package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.constants.UserType;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.exception.BlockException;
import com.zhazha.cqbot.exception.NotifyException;
import com.zhazha.cqbot.service.UserService;
import com.zhazha.cqbot.utils.PermissionUtils;
import com.zhazha.cqbot.utils.ReplyUtils;
import kotlin.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
public class UserMessageFilter implements MessageFilter {
    
    @Resource
    private UserService userService;
    public static final String CMD_USER_ADD = Constants.CMD_USER + "add";
    public static final String CMD_USER_BLK = Constants.CMD_USER + "block";
    public static final String CMD_USER_DEL = Constants.CMD_USER + "del";
    public static final String CMD_USER_GET = Constants.CMD_USER + "get";
    public static final String CMD_USER_LIST = Constants.CMD_USER + "list";
    public static final String CMD_USER_HELP = Constants.CMD_USER + "help";
    
    @Override
    public Boolean match(BaseVO vo) {
        try {
            MessageVO messageVO = (MessageVO) vo;
            String raw_message = getRawMessage(messageVO);
            return StrUtil.startWithIgnoreCase(raw_message, Constants.CMD_USER);
        } catch (Exception ignored) {
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        MessageVO messageVO = (MessageVO) vo;
        
        Long sendUserId = messageVO.getUser_id();
        User sendUser = userService.get(sendUserId);
        if (null == sendUser) {
            throw new NotifyException("你没有权限");
        }
        if (!StrUtil.equalsIgnoreCase(sendUser.getType(), UserType.ADMIN.name())
                && !StrUtil.equalsIgnoreCase(sendUserId.toString(), Constants.adminQQ)) {
            throw new NotifyException("你没有权限");
        }
        
        String raw_message = getRawMessage(messageVO);
        
        if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_ADD)) {
            // 用户注册
            return addUser(sendUserId, raw_message);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_GET)) {
            // 读取
            return getUser(raw_message);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_BLK)) {
            // 拉黑
            return blockUser(sendUser, messageVO);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_LIST)) {
            // 列出授权列表
            return listUser();
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_DEL)) {
            // 删除
            return deleteUser(sendUser, raw_message);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_HELP)) {
            // 删除
            return help();
        }
        
        return ReplyUtils.build("指令不正确\n");
    }
    
    private ReplyVO help() {
        return ReplyUtils.build(
                UserMessageFilter.CMD_USER_ADD + " qq\n" +
                        UserMessageFilter.CMD_USER_BLK + " qq\n" +
                        UserMessageFilter.CMD_USER_DEL + " qq\n" +
                        UserMessageFilter.CMD_USER_GET + " qq\n" +
                        UserMessageFilter.CMD_USER_LIST + "\n" +
                        UserMessageFilter.CMD_USER_HELP
        );
    }
    
    private ReplyVO deleteUser(User sendUser, String raw_message) {
        // 你的权限是否大于即将操作的权限?
        String qq = raw_message.replaceFirst(CMD_USER_DEL, "").trim();
        if (StrUtil.isBlank(qq)) {
            return ReplyUtils.build("没有该用户");
        }
        User user = userService.get(qq);
        if (null == user) {
            return ReplyUtils.build("没有数据");
        }
        if (PermissionUtils.hasPermission(sendUser.getType(), user.getType())) {
            userService.removeById(qq);
        }
        return ReplyUtils.build("删除成功");
    }
    
    private ReplyVO listUser() {
        List<User> list = userService.listUser();
        return ReplyUtils.build(Arrays.toString(list.stream().map(user -> new Pair<>(user.getQq(), user.getType()) + "\n").toArray()));
    }
    
    private ReplyVO getUser(String raw_message) {
        String qq = raw_message.replaceFirst(CMD_USER_GET, "").trim();
        User user = userService.get(qq);
        if (user == null) {
            return ReplyUtils.build("没有数据");
        }
        if (StrUtil.equalsIgnoreCase(user.getType(), UserType.USER.name())) {
            return ReplyUtils.build("读取成功: " + user);
        }
        return ReplyUtils.build("没有User数据");
    }
    
    private ReplyVO blockUser(User sendUser, MessageVO messageVO) {
        // 你的权限是否大于即将操作的权限?
        String rawMessage = StrUtil.trimStart(messageVO.getRaw_message());
        String qq = rawMessage.replaceFirst(CMD_USER_BLK, "").trim();
        User user = userService.get(qq);
        if (null == user) {
            user = User.builder().qq(qq)
                    .createQq(messageVO.getUser_id().toString())
                    .build();
        }
        if (PermissionUtils.hasPermission(sendUser.getType(), user.getType())) {
            user.setType(UserType.BLOCK.name());
            userService.saveOrUpdate(user);
        }
        return ReplyUtils.build("拉黑成功");
    }
    
    private ReplyVO addUser(Long userId, String raw_message) {
        // 发送者必须是 admin bot or super 角色才有权限添加 user 用户
        // 被注册者如果是黑名单用户, 则失败
        // 被注册者如果已经注册则失败
        String qq = raw_message.replace(CMD_USER_ADD, "").trim();
        User one = userService.get(qq);
        if (one != null) {
            if (StrUtil.equalsIgnoreCase(one.getType(), UserType.BLOCK.name())) {
                throw new BlockException("您是黑名单用户, 请联系管理员!!!");
            }
            throw new NotifyException("该用户已注册");
        }
        User user = User.builder()
                .type(UserType.USER.name())
                .createQq(userId.toString())
                .qq(qq)
                .build();
        userService.saveOrUpdate(user);
        return ReplyUtils.build("注册成功");
    }
    
}
