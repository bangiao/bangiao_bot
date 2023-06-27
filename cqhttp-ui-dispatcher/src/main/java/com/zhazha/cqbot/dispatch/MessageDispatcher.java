package com.zhazha.cqbot.dispatch;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.handler.IMessageHandler;
import com.zhazha.cqbot.handler.MetaEventIMessageHandler;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class MessageDispatcher {
    
    @Resource
    private Map<String, IMessageHandler> messageHandlerMap;
    
    public ReplyVO dispatch(Map<String, Object> maps) throws Exception {
        String post_type = (String) maps.get("post_type");
        if (StrUtil.isBlank(post_type)) {
            throw new RuntimeException("消息类型不对");
        }
        
        IMessageHandler IMessageHandler = Optional.ofNullable(messageHandlerMap.get(post_type))
                .orElse(new MetaEventIMessageHandler());
        
        return IMessageHandler.handler(maps);
    }
}
