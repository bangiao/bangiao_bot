package com.zhazha.cqhttp.handler;

import com.zhazha.cqhttp.vo.ReplyVO;

import java.util.Map;

//		message, message_sent, request, notice, meta_event
public interface IMessageHandler {
	
	ReplyVO handler(Map<String, Object> message) throws Exception;
	
}
