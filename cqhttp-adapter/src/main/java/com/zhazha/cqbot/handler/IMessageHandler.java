package com.zhazha.cqbot.handler;

import com.zhazha.cqhttp.vo.ReplyVO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

//		message, message_sent, request, notice, meta_event
public interface IMessageHandler {
    
    @Nullable
    ReplyVO handler(@NotNull Map<String, Object> message) throws Exception;

}
