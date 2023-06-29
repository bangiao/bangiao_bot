package com.zhazha.cqhttp.command.admin;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class DeleteAdminCommand implements AdminCommand {
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdAdminEnum getMode() {
        return CmdAdminEnum.CMD_ADMIN_DEL;
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        String qq = adminMessage.getQq(getMode().getCmd());
        if (StrUtil.isBlank(qq)) {
            return ReplyVO.build("没有该用户");
        }
        userRepository.removeById(qq);
        return ReplyVO.build("删除成功");
    }
}
