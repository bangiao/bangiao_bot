package com.zhazha.cqhttp.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.chat.ChatEngine;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.utils.CQCodeUtils;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import com.zhazha.cqhttp.vo.UserMessage;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AtGroupMessageFilter implements MessageFilter {
    
    @Resource
    private ChatEngine chatEngine;
    
    @Override
    public Boolean match(BaseVO vo) {
        UserMessage userMessage = (UserMessage) vo;
        String raw_message = userMessage.getRaw_message();
        return StrUtil.containsIgnoreCase(raw_message, Constants.AT_BOT);
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        UserMessage userMessage = (UserMessage) vo;
        Set<String> at = CQCodeUtils.getAtWithout(userMessage.getRaw_message());
        
        String response = chatEngine.execute(userMessage);
        
        // [CQ:at,qq=222222] [CQ:at,qq=11111] 开始
        String message = buildMessage(at, response);
        return ReplyVO.builder().reply(message).at_sender(true).build();
    }
    
    private String buildMessage(Set<String> at, String response) {
        StringBuilder cqBuilder = new StringBuilder();
        at.forEach(qq -> cqBuilder.append("[CQ:at,qq=").append(qq).append("] "));
        cqBuilder.append(response);
        return cqBuilder.toString();
    }
}
