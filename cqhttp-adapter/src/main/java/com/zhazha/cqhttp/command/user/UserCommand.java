package com.zhazha.cqhttp.command.user;

import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;

public interface UserCommand {
    CmdUserEnum getMode();
    boolean matches(String rawMessage);
    ReplyVO execute(UserMessage userMessage);
}