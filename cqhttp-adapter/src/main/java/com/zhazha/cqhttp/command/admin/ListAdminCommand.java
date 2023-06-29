package com.zhazha.cqhttp.command.admin;

import cn.hutool.core.lang.Pair;
import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ListAdminCommand implements AdminCommand {
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdAdminEnum getMode() {
        return CmdAdminEnum.CMD_ADMIN_LIST;
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        List<User> list = userRepository.list();
        return ReplyVO.build(Arrays.toString(list.stream().map(user -> new Pair<>(user.getQq(), user.getType()).toString() + "\n").toArray()));
    }
}
