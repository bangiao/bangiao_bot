package com.zhazha.cqhttp.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.zhazha.cqhttp.vo.MessageVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
public class FilterChainManager {
    
    @Resource
    private UserMessageFilter userMessageFilter;
    @Resource
    private AtGroupMessageFilter atGroupMessageFilter;
    @Resource
    private ChatMessageFilter chatMessageFilter;
    
    public ReplyVO createMessageFilterChain(MessageVO vo) throws Exception {
        MessageFilterChain messageFilterChain = SpringUtil.getBean(MessageFilterChain.class);
        String messageType = vo.getMessage_type();
        if (StrUtil.equalsIgnoreCase(messageType, "private")) {
            // 好友消息或者群里私聊你的消息
            messageFilterChain.addFilter(userMessageFilter);
            messageFilterChain.addFilter(chatMessageFilter);
        } else {
            // 群消息
            messageFilterChain.addFilter(atGroupMessageFilter);
            messageFilterChain.addFilter(chatMessageFilter);
        }
        return messageFilterChain.doChain(vo, messageFilterChain);
    }
    
}
