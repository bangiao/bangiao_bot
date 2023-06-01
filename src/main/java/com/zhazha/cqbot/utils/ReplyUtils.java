package com.zhazha.cqbot.utils;

import com.zhazha.cqbot.controller.vo.ReplyVO;

public class ReplyUtils {
    
    public static ReplyVO build(String content) {
        return ReplyVO.builder()
                .auto_escape(false)
                .at_sender(true)
                .reply(content)
                .build();
    }
    
}
