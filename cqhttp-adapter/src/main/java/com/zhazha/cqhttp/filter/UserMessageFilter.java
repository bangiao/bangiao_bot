package com.zhazha.cqhttp.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.command.user.UserCommandExecutor;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.MessageVO;
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
            MessageVO messageVO = (MessageVO) vo;
            return StrUtil.startWithIgnoreCase(messageVO.getRaw_message(), Constants.CMD_USER);
        } catch (Exception ignored) {
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        MessageVO messageVO = (MessageVO) vo;
        UserMessage userMessage = BeanUtil.toBean(messageVO, UserMessage.class);
        User sendUser = userRepository.get(userMessage.getUser_id());
        userMessage.checkUserPermission(sendUser);
        return userCommandExecutor.executeCommand(userMessage);
    }
}
