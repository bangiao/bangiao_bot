package com.zhazha.cqhttp.command.chat;

import cn.hutool.core.text.StrJoiner;
import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelpChatCommand implements ChatCommand{
    @Override
    public CmdChatEnum getMode() {
        return CmdChatEnum.CMD_CHAT_HELP;
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        List<String> allCmd = CmdChatEnum.getAllCmd();
        return ReplyVO.build(StrJoiner.of("\n").append(allCmd).toString());
    }
}
