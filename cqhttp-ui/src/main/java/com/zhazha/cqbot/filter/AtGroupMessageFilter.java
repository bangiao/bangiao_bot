package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.chat.ChatEngine;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.utils.CQCodeUtils;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.MessageVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AtGroupMessageFilter implements MessageFilter {
    
    @Resource
    private ChatEngine chatEngine;
    
    @Override
    public Boolean match(BaseVO vo) {
        MessageVO messageVO = (MessageVO) vo;
        String raw_message = getRawMessage(messageVO);
        return StrUtil.containsIgnoreCase(raw_message, Constants.AT_BOT);
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        MessageVO messageVO = (MessageVO) vo;
        Set<String> at = CQCodeUtils.getAtWithout(messageVO.getRaw_message());
        
        String response = chatEngine.execute(messageVO);
        
        // [CQ:at,qq=222222] [CQ:at,qq=11111] 开始
        String message = buildMessage(at, response);
        return ReplyVO.builder().reply(message).at_sender(true).build();
    }
    
    @NotNull
    private String buildMessage(Set<String> at, String response) {
        StringBuilder cqBuilder = new StringBuilder();
        at.forEach(qq -> cqBuilder.append("[CQ:at,qq=").append(qq).append("] "));
        cqBuilder.append(response);
        return cqBuilder.toString();
    }
}
