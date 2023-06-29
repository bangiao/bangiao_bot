package com.zhazha.cqhttp.command.admin;

import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminCommandExecutor {
    
    @Resource
    private List<AdminCommand> commands;
    
    public ReplyVO executeCommand(AdminMessage adminMessage) {
        String rawMessage = adminMessage.getRaw_message();
        for (AdminCommand command : commands) {
            if (command.matches(rawMessage)) {
                return command.execute(adminMessage);
            }
        }
        return ReplyVO.build("指令不正确\n"); // 没有匹配的命令
    }
    
}
