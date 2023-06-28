package com.zhazha.cqbot.handler;

import com.zhazha.cqhttp.vo.ReplyVO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("request")
public class RequestMessageHandler implements IMessageHandler {
    @Nullable
    @Override
    public ReplyVO handler(@NotNull Map<String, Object> message) throws Exception {
        return ReplyVO.builder()
                .at_sender(true)
                .reply("2")
                .build();
    }
}
