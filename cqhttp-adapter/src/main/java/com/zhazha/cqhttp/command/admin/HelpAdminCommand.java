package com.zhazha.cqhttp.command.admin;

import cn.hutool.core.text.StrJoiner;
import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import org.springframework.stereotype.Component;

@Component
public class HelpAdminCommand implements AdminCommand {
    @Override
    public CmdAdminEnum getMode() {
        return CmdAdminEnum.CMD_ADMIN_HELP;
    }
    
    @Override
    public boolean matches(String rawMessage) {
        return getMode().match(rawMessage);
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        return ReplyVO.build(StrJoiner.of("\n").append(CmdAdminEnum.getAllCmd()).toString());
    }
}
