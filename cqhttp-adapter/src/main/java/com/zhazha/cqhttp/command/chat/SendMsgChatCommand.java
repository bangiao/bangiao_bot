package com.zhazha.cqhttp.command.chat;

import com.zhazha.cqhttp.chat.ChatEngine;
import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class SendMsgChatCommand implements ChatCommand {
    @Resource
    private ChatEngine chatEngine;
    
    @Override
    public CmdChatEnum getMode() {
        return CmdChatEnum.CMD_CHAT_SEND;
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        String rawMessage = adminMessage.getRaw_message();
        String msg = rawMessage.replace(getMode().getCmd(), "");
        adminMessage.setRaw_message(msg);
        String response = chatEngine.execute(adminMessage);
        return ReplyVO.builder()
                .at_sender(true)
                .reply(response)
                .build();
    }
}
