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
import com.zhazha.cqbot.utils.ReplyUtils;
import kotlin.Pair;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class AdminMessageFilter implements MessageFilter {
    
    @Resource
    private UserService userService;
    public static final String CMD_ADMIN_ADD = Constants.CMD_ADMIN + "add";
    public static final String CMD_ADMIN_BLK = Constants.CMD_ADMIN + "block";
    public static final String CMD_ADMIN_DEL = Constants.CMD_ADMIN + "del";
    public static final String CMD_ADMIN_GET = Constants.CMD_ADMIN + "get";
    public static final String CMD_ADMIN_LIST = Constants.CMD_ADMIN + "list";
    
    @Override
    public Boolean match(BaseVO vo) {
        try {
            MessageVO messageVO = (MessageVO) vo;
            String raw_message = messageVO.getRaw_message();
            return StrUtil.startWithIgnoreCase(StrUtil.trimStart(raw_message), Constants.CMD_ADMIN);
        } catch (Exception ignored) {
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        MessageVO messageVO = (MessageVO) vo;
        
        Long sendUserId = messageVO.getUser_id();
        if (!StrUtil.equalsIgnoreCase(sendUserId.toString(), Constants.adminQQ)) {
            throw new NotifyException("你没有权限");
        }
        
        String raw_message = StrUtil.trimStart(messageVO.getRaw_message());
        
        if (StrUtil.startWithIgnoreCase(raw_message,CMD_ADMIN_ADD)) {
            // 用户注册
            return addAdmin(sendUserId, raw_message);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_ADMIN_GET)) {
            // 读取
            return getAdmin(raw_message);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_ADMIN_BLK)) {
            // 拉黑
            return blockAdmin(messageVO);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_ADMIN_LIST)) {
            // 列出授权列表
            return listAdmin();
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_ADMIN_DEL)) {
            // 删除
            return deleteAdmin(raw_message);
        }
        return ReplyUtils.build("指令不正确\n");
    }
    
    private ReplyVO deleteAdmin(String raw_message) {
        // 你的权限是否大于即将操作的权限?
        String qq = raw_message.replaceFirst(CMD_ADMIN_DEL, "").trim();
        if (StrUtil.isBlank(qq)) {
            return ReplyUtils.build("没有该用户");
        }
        userService.removeById(qq);
        return ReplyUtils.build("删除成功");
    }
    
    private ReplyVO listAdmin() {
        List<User> list = userService.listAdmin();
        return ReplyUtils.build(Arrays.toString(list.stream().map(user -> new Pair<>(user.getQq(), user.getType()).toString() + "\n").toArray()));
    }
    
    private ReplyVO getAdmin(String raw_message) {
        String qq = raw_message.replaceFirst(CMD_ADMIN_GET, "").trim();
        User user = userService.getAdmin(qq);
        if (user == null) {
            return ReplyUtils.build("没有数据");
        }
        return ReplyUtils.build("读取成功: " + user);
    }
    
    private ReplyVO blockAdmin(MessageVO messageVO) {
        String rawMessage = StrUtil.trimStart(messageVO.getRaw_message());
        String qq = rawMessage.replaceFirst(CMD_ADMIN_BLK, "").trim();
        User user = userService.getAdmin(qq);
        if (null == user) {
            user = User.builder().qq(qq)
                    .createQq(messageVO.getUser_id().toString())
                    .build();
        }
        user.setType(UserType.BLOCK.name());
        userService.saveOrUpdate(user);
        return ReplyUtils.build("拉黑成功");
    }
    
    private ReplyVO addAdmin(Long userId, String raw_message) {
        // 被注册用户是否是黑名单用户
        // 被注册用户是否已经注册
        String qq = raw_message.replace(CMD_ADMIN_ADD, "").trim();
        User one = userService.get(qq);
        if (one != null) {
            if (StrUtil.equalsIgnoreCase(one.getType(), UserType.BLOCK.name())) {
                throw new BlockException("您是黑名单用户, 请联系管理员!!!");
            }
            throw new NotifyException("该用户已注册");
        }
        User user = User.builder()
                .type(UserType.ADMIN.name())
                .createQq(userId.toString())
                .qq(qq)
                .build();
        userService.saveOrUpdate(user);
        return ReplyUtils.build("注册成功");
    }
}
