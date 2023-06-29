package com.zhazha.cqhttp.command.chat;

import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatCommandExecutor {
    
    @Resource
    private List<ChatCommand> commands;
    
    public ReplyVO executeCommand(AdminMessage userMessage) {
        String rawMessage = userMessage.getRaw_message();
        for (ChatCommand command : commands) {
            if (command.matches(rawMessage)) {
                return command.execute(userMessage);
            }
        }
        return ReplyVO.build("指令不正确\n"); // 没有匹配的命令
    }
    
}
