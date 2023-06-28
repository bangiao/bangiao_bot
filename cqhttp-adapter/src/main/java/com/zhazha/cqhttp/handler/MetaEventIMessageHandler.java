package com.zhazha.cqhttp.handler;

import com.zhazha.cqhttp.vo.ReplyVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("meta_event")
public class MetaEventIMessageHandler implements IMessageHandler {
    @Override
    public ReplyVO handler(@NotNull Map<String, Object> message) {
        return null;
    }
}
