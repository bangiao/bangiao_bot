package com.zhazha.cqbot.request;

import com.zhazha.cqbot.exception.ChatException;

public interface ChatRequest {
	
	String request(Long qq, String question) throws ChatException;
	
}
