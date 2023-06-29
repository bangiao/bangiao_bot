package com.zhazha.cqhttp.command.user;

import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.exception.NotifyException;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.utils.PermissionUtils;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteUserCommand implements UserCommand {
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdUserEnum getMode() {
        return CmdUserEnum.CMD_USER_DEL;
    }
    
    @Override
    public boolean matches(String rawMessage) {
        return getMode().match(rawMessage);
    }
    
    @Override
    public ReplyVO execute(UserMessage userMessage) {
        // 你的权限是否大于即将操作的权限?
        User sender = getSender(userMessage);
        String qq = userMessage.getContentQq(getMode().getCmd());
        User user = getTargetQq(qq);
        if (PermissionUtils.hasPermission(sender.getType(), user.getType())) {
            userRepository.removeById(qq);
        }
        return ReplyVO.build("删除成功");
    }
    
    @NotNull
    private User getTargetQq(String qq) {
        User user = userRepository.get(qq);
        if (null == user) {
            throw new NotifyException("操作失败");
        }
        return user;
    }
    
    @NotNull
    private User getSender(UserMessage userMessage) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.getUser(userMessage.getUser_id()));
        if (userOptional.isEmpty()) {
            throw new NotifyException("没有权限");
        }
        return userOptional.get();
    }
}
