package com.zhazha.cqhttp.command.admin;

import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;

public interface AdminCommand {
    CmdAdminEnum getMode();
    
    ReplyVO execute(AdminMessage adminMessage);
    
    default boolean matches(String rawMessage) {
        return getMode().match(rawMessage);
    }
}
