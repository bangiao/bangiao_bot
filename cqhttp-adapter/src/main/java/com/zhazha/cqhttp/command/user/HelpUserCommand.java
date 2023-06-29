package com.zhazha.cqhttp.command.user;

import cn.hutool.core.text.StrJoiner;
import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HelpUserCommand implements UserCommand {
    @Override
    public CmdUserEnum getMode() {
        return CmdUserEnum.CMD_USER_HELP;
    }
    
    @Override
    public ReplyVO execute(UserMessage userMessage) {
        List<String> allCmd = CmdUserEnum.getAllCmd();
        return ReplyVO.build(StrJoiner.of("\n").append(allCmd).toString());
    }
}
