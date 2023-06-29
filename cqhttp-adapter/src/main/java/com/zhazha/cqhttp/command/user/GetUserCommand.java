package com.zhazha.cqhttp.command.user;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.constants.UserType;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class GetUserCommand implements UserCommand{
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdUserEnum getMode() {
        return CmdUserEnum.CMD_USER_GET;
    }
    
    @Override
    public ReplyVO execute(UserMessage userMessage) {
        String raw_message = userMessage.getRaw_message();
        
        String qq = raw_message.replaceFirst(getMode().getCmd(), "").trim();
        User user = userRepository.get(qq);
        if (user == null) {
            return ReplyVO.build("没有数据");
        }
        if (StrUtil.equalsIgnoreCase(user.getType(), UserType.USER.name())) {
            return ReplyVO.build("读取成功: " + user);
        }
        return ReplyVO.build("没有User数据");
    }
}
