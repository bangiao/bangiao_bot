package com.zhazha.cqhttp.request;

import com.zhazha.cqhttp.exception.ChatException;

public interface ChatRequest {
	
	Boolean match(String key);
	
	String request(String question) throws ChatException;
	
}
