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
import kotlin.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
public class UserMessageFilter implements MessageFilter {
    
    @Resource
    private UserService userService;
    public static final String CMD_USER_REG = Constants.CMD_USER + "register ";
    public static final String CMD_USER_BLK = Constants.CMD_USER + "block ";
    public static final String CMD_USER_DEL = Constants.CMD_USER + "del ";
    public static final String CMD_USER_GET = Constants.CMD_USER + "get ";
    public static final String CMD_USER_LIST = Constants.CMD_USER + "list";
    
    @Override
    public Boolean match(BaseVO vo) {
        try {
            MessageVO messageVO = (MessageVO) vo;
            String raw_message = messageVO.getRaw_message();
            return StrUtil.startWithIgnoreCase(raw_message, Constants.CMD_USER);
        } catch (Exception ignored) {
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        MessageVO messageVO = (MessageVO) vo;
        
        Long sendUserId = messageVO.getUser_id();
        User sendUser = userService.getUser(sendUserId);
        if (null == sendUser) {
            throw new NotifyException("你没有权限");
        }
        if (!StrUtil.equalsIgnoreCase(sendUser.getType(), UserType.ADMIN.name())
                && !StrUtil.equalsIgnoreCase(sendUserId.toString(), Constants.adminQQ)) {
            throw new NotifyException("你没有权限");
        }
        
        String raw_message = messageVO.getRaw_message();
        
        if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_REG)) {
            // 用户注册
            return registerUser(sendUserId, raw_message);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_GET)) {
            // 读取
            return getUser(raw_message);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_BLK)) {
            // 拉黑
            return blockUser(messageVO);
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_LIST)) {
            // 列出授权列表
            return listUser();
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_DEL)) {
            // 删除
            return deleteUser(raw_message);
        }
        return null;
    }
    
    private ReplyVO deleteUser(String raw_message) {
        String qq = raw_message.replaceFirst(CMD_USER_DEL, "").trim();
        if (StrUtil.isBlank(qq)) {
            return ReplyVO.builder()
                    .auto_escape(true)
                    .reply("没有该用户")
                    .at_sender(true)
                    .build();
        }
        User user = userService.getUser(qq);
        if (StrUtil.equalsIgnoreCase(user.getType(), UserType.ADMIN.name())) {
            return ReplyVO.builder()
                    .auto_escape(true)
                    .reply("管理员不能删除管理员权限")
                    .at_sender(true)
                    .build();
        }
        userService.removeById(qq);
        return ReplyVO.builder()
                .auto_escape(true)
                .reply("删除成功")
                .at_sender(true)
                .build();
    }
    
    private ReplyVO listUser() {
        List<User> list = userService.list();
        return ReplyVO.builder()
                .auto_escape(true)
                .reply(Arrays.toString(list.stream().map(user -> new Pair<>(user.getQq(), user.getType())).toArray()))
                .at_sender(true)
                .build();
    }
    
    private ReplyVO getUser(String raw_message) {
        String qq = raw_message.replaceFirst(CMD_USER_GET, "").trim();
        User user = userService.getUser(qq);
        if (user == null) {
            return ReplyVO.builder()
                    .at_sender(true)
                    .reply("没有数据").build();
        }
        return ReplyVO.builder()
                .auto_escape(true)
                .reply("读取成功: " + user)
                .at_sender(true)
                .build();
    }
    
    private ReplyVO blockUser(MessageVO messageVO) {
        String rawMessage = messageVO.getRaw_message();
        String qq = rawMessage.replaceFirst(CMD_USER_BLK, "").trim();
        User user = userService.getUser(qq);
        if (null == user) {
            user = User.builder().qq(qq)
                    .createQq(messageVO.getUser_id().toString())
                    .build();
        }
        user.setType(UserType.BLOCK.name());
        userService.saveOrUpdate(user);
        return ReplyVO.builder()
                .auto_escape(true)
                .reply("拉黑成功")
                .at_sender(true)
                .build();
    }
    
    private ReplyVO registerUser(Long userId, String raw_message) {
        // 1. 检查用户是否已经存在
        // 2. 注册
        String qq = raw_message.replace(CMD_USER_REG, "").trim();
        User one = userService.getUser(qq);
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
        return ReplyVO.builder()
                .auto_escape(true)
                .reply("注册成功")
                .at_sender(true)
                .build();
    }
    
}
