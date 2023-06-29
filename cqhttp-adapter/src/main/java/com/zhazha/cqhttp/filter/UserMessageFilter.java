package com.zhazha.cqhttp.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.command.user.UserCommandExecutor;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class UserMessageFilter implements MessageFilter {
    
    @Resource
    private UserRepository userRepository;
    @Resource
    private UserCommandExecutor userCommandExecutor;
    
    @Override
    public Boolean match(BaseVO vo) {
        try {
            UserMessage userMessage = (UserMessage) vo;
            return StrUtil.startWithIgnoreCase(userMessage.getRaw_message(), Constants.CMD_USER);
        } catch (Exception ignored) {
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        UserMessage userMessage = (UserMessage) vo;
        User sendUser = userRepository.get(userMessage.getUser_id());
        userMessage.checkUserPermission(sendUser);
        return userCommandExecutor.executeCommand(userMessage);
    }
}
