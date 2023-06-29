package com.zhazha.cqhttp.command.user;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.constants.UserType;
import com.zhazha.cqhttp.exception.BlockException;
import com.zhazha.cqhttp.exception.NotifyException;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AddUserCommand implements UserCommand {
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdUserEnum getMode() {
        return CmdUserEnum.CMD_USER_ADD;
    }
    
    @Override
    public ReplyVO execute(UserMessage userMessage) {
        String rawMessage = userMessage.getRaw_message();
        Long userId = userMessage.getUser_id();
        String qq = rawMessage.replace(getMode().getCmd(), "").trim();
        User one = userRepository.get(qq);
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
        userRepository.saveOrUpdate(user);
        return ReplyVO.build("注册成功");
    }
}
