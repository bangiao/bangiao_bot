package com.zhazha.cqhttp.command.admin;

import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.constants.UserType;
import com.zhazha.cqhttp.exception.NotifyException;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AddAdminCommand implements AdminCommand {
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdAdminEnum getMode() {
        return CmdAdminEnum.CMD_ADMIN_ADD;
    }
    
    @Override
    public boolean matches(String rawMessage) {
        return getMode().match(rawMessage);
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        // 被注册用户是否已经注册
        String qq = adminMessage.getQq(getMode().getCmd());
        addCheck(qq);
        User user = User.builder()
                .type(UserType.ADMIN.name())
                .createQq(adminMessage.getUser_id().toString())
                .qq(qq)
                .build();
        userRepository.saveOrUpdate(user);
        return ReplyVO.build("注册成功");
    }
    
    private void addCheck(String qq) {
        User one = userRepository.get(qq);
        if (one != null) {
            throw new NotifyException("该用户已注册");
        }
    }
}
