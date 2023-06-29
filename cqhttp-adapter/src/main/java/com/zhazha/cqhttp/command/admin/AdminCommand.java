package com.zhazha.cqhttp.command.admin;

import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;

public interface AdminCommand {
    CmdAdminEnum getMode();
    boolean matches(String rawMessage);
    ReplyVO execute(AdminMessage adminMessage);
    
}
