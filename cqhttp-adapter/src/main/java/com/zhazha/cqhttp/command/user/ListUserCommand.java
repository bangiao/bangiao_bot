package com.zhazha.cqhttp.command.user;

import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import jakarta.annotation.Resource;
import kotlin.Pair;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ListUserCommand implements UserCommand {
    
    @Resource
    private UserRepository userRepository;
    
    @Override
    public CmdUserEnum getMode() {
        return CmdUserEnum.CMD_USER_LIST;
    }
    
    @Override
    public ReplyVO execute(UserMessage userMessage) {
        List<User> list = userRepository.listUser();
        return ReplyVO.build(Arrays.toString(
                list.stream().map(user -> new Pair<>(user.getQq(), user.getType()) + "\n").toArray()));
    }
}
