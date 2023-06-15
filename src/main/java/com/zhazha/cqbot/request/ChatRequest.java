package com.zhazha.cqbot.request;

import com.zhazha.cqbot.exception.ChatException;

public interface ChatRequest {
	
	Boolean match(String key);
	
	String request(String question) throws ChatException;
	
}
