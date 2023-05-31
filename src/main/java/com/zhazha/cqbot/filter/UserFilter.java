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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Component
public class UserFilter implements MessageFilter {
    
    @Resource
    private UserService userService;
    private final String CMD_USER = "#user ";
    private final String CMD_USER_REG = CMD_USER + "register ";
    private final String CMD_USER_BLK = CMD_USER + "block ";
    private final String CMD_USER_DEL = CMD_USER + "delete ";
    private final String CMD_USER_GET = CMD_USER + "get ";
    private final String CMD_USER_LIST = CMD_USER + "list ";
    
    @Override
    public Boolean match(BaseVO vo) {
        try {
            MessageVO messageVO = (MessageVO) vo;
            String raw_message = messageVO.getRaw_message();
            return StrUtil.startWithIgnoreCase(raw_message, CMD_USER);
        } catch (Exception ignored) {
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
        if (!match(vo)) {
            return chain.doChain(vo, chain);
        }
        
        MessageVO messageVO = (MessageVO) vo;
        
        Long userId = messageVO.getUser_id();
        User byId = userService.getById(userId);
        if (!StrUtil.equalsIgnoreCase(byId.getType(), UserType.ADMIN.name())
                && !StrUtil.equalsIgnoreCase(userId.toString(), Constants.adminQQ)) {
            throw new NotifyException("你没有权限");
        }
        
        String raw_message = messageVO.getRaw_message();
        
        if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_REG)) {
            // 用户注册
            
            // 1. 检查用户是否已经存在
            // 2. 注册
            String qq = raw_message.replace(CMD_USER_REG, "").trim();
            User one = userService.getById(Long.valueOf(qq));
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
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_GET)) {
            // 读取
            String qq = raw_message.replaceFirst(CMD_USER_GET, "").trim();
            User user = userService.getById(qq);
            if (user == null) {
            
            }
            return ReplyVO.builder()
                    .auto_escape(true)
                    .reply("读取成功: " + user.toString())
                    .at_sender(true)
                    .build();
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_BLK)) {
            // 拉黑
            String qq = raw_message.replaceFirst(CMD_USER_BLK, "").trim();
            User user = userService.getById(qq);
            user.setType(UserType.BLOCK.name());
            userService.saveOrUpdate(user);
            return ReplyVO.builder()
                    .auto_escape(true)
                    .reply("拉黑成功")
                    .at_sender(true)
                    .build();
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_LIST)) {
            // 列出授权列表
            List<User> list = userService.list();
            return ReplyVO.builder()
                    .auto_escape(true)
                    .reply(Arrays.toString(list.stream().map(User::getQq).toArray()))
                    .at_sender(true)
                    .build();
        } else if (StrUtil.startWithIgnoreCase(raw_message, CMD_USER_DEL)) {
            // 删除
            String qq = raw_message.replaceFirst(CMD_USER_DEL, "").trim();
            userService.removeById(qq);
            return ReplyVO.builder()
                    .auto_escape(true)
                    .reply("删除成功")
                    .at_sender(true)
                    .build();
        }
        
        return null;
    }
    
    // register user 2222222222
    private void register(String senderId, String qq, String type) {
        User one = userService.lambdaQuery().eq(User::getQq, qq)
                .one();
        if (null == one) {
            User user = User.builder()
                    .qq(qq)
                    .createQq(senderId)
                    .type(type)
                    .build();
            userService.saveOrUpdate(user);
        } else {
            throw new NotifyException("该用户已有权限");
        }
    }
}
