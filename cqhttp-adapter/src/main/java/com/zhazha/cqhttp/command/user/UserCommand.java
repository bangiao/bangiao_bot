package com.zhazha.cqhttp.command.user;

import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;

public interface UserCommand {
    CmdUserEnum getMode();
    ReplyVO execute(UserMessage userMessage);
    
    default boolean matches(String rawMessage) {
        return getMode().match(rawMessage);
    }
}