package com.zhazha.cqhttp.command.user;

import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCommandExecutor {
    
    @Resource
    private List<UserCommand> commands;
    
    public ReplyVO executeCommand(UserMessage userMessage) {
        String rawMessage = userMessage.getRaw_message();
        for (UserCommand command : commands) {
            if (command.matches(rawMessage)) {
                return command.execute(userMessage);
            }
        }
        return ReplyVO.build("指令不正确\n"); // 没有匹配的命令
    }
    
}
