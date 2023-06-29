package com.zhazha.cqhttp.command.admin;

import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class GetAdminCommand implements AdminCommand {
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdAdminEnum getMode() {
        return CmdAdminEnum.CMD_ADMIN_GET;
    }
    
    @Override
    public boolean matches(String rawMessage) {
        return getMode().match(rawMessage);
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        String qq = adminMessage.getQq(getMode().getCmd());
        User user = userRepository.getAdmin(qq);
        if (user == null) {
            return ReplyVO.build("没有数据");
        }
        return ReplyVO.build("读取成功: " + user);
    }
}
