package com.zhazha.cqbot.handler;

import cn.hutool.core.bean.BeanUtil;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.filter.FilterChainManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("message")
public class MessageHandler implements IMessageHandler {
    
        @Resource
        private FilterChainManager filterChainManager;
    
    @Nullable
    @Override
    public ReplyVO handler(@NotNull Map<String, Object> message) throws Exception {
        MessageVO messageVO = BeanUtil.toBean(message, MessageVO.class);
        return filterChainManager.createMessageFilterChain(messageVO);
    }
}
