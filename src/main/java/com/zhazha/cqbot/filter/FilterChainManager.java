package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Data
@Slf4j
public class FilterChainManager {
    
    @Resource
    private UserFilter userFilter;
    @Resource
    private AtGroupMessageFilter atGroupMessageFilter;
    @Resource
    private ChatMessageFilter chatMessageFilter;
    
    public ReplyVO createMessageFilterChain(MessageVO vo) throws Exception {
        MessageFilterChain messageFilterChain = SpringUtil.getBean(MessageFilterChain.class);
        String messageType = vo.getMessage_type();
        if (StrUtil.equalsIgnoreCase(messageType, "private")) {
            // 好友消息或者群里私聊你的消息
            messageFilterChain.addFilter(userFilter);
            messageFilterChain.addFilter(chatMessageFilter);
        } else {
            // 群消息
            messageFilterChain.addFilter(atGroupMessageFilter);
            messageFilterChain.addFilter(chatMessageFilter);
        }
        return messageFilterChain.doChain(vo, messageFilterChain);
    }
    
}
