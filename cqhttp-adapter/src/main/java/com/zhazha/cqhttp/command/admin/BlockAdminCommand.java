package com.zhazha.cqhttp.command.admin;

import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class BlockAdminCommand implements AdminCommand {
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdAdminEnum getMode() {
        return CmdAdminEnum.CMD_ADMIN_BLK;
    }
    
    @Override
    public boolean matches(String rawMessage) {
        return getMode().match(rawMessage);
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        String qq = adminMessage.getQq(getMode().getCmd());
        User user = userRepository.getAdmin(qq);
        if (null == user) {
            user = User.builder().qq(qq)
                    .createQq(adminMessage.getUser_id().toString())
                    .build();
        }
        user.blockUser();
        userRepository.saveOrUpdate(user);
        return ReplyVO.build("拉黑成功");
    }
}
