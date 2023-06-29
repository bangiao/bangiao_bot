package com.zhazha.cqhttp.command.user;

import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.exception.NotifyException;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BlockUserCommand implements UserCommand {
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdUserEnum getMode() {
        return CmdUserEnum.CMD_USER_BLK;
    }
    
    @Override
    public ReplyVO execute(UserMessage userMessage) {
        // 你的权限是否大于即将操作的权限?
        User sendUser = checkSendUserPermission(userMessage);
        User user = getTargetQq(userMessage);
        if (sendUser.hasPermission(user)) {
            user.blockUser();
            userRepository.saveOrUpdate(user);
        }
        return ReplyVO.build("拉黑成功");
    }
    
    private User getTargetQq(UserMessage userMessage) {
        String qq = userMessage.getContentQq(getMode().getCmd());
        User user = userRepository.get(qq);
        if (null == user) {
            user = User.builder().qq(qq)
                    .createQq(userMessage.getUser_id().toString())
                    .build();
        }
        return user;
    }
    
    @NotNull
    private User checkSendUserPermission(UserMessage userMessage) {
        Long userId = userMessage.getUser_id();
        Optional<User> userOptional = Optional.ofNullable(userRepository.getUser(userId));
        if (userOptional.isEmpty()) {
            throw new NotifyException("你没有权限");
        }
        return userOptional.get();
    }
}
