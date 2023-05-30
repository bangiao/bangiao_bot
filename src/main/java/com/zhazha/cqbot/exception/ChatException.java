package com.zhazha.cqbot.exception;

public class ChatException extends RuntimeException {
	public ChatException() {
	}
	
	public ChatException(String message) {
		super(message);
	}
}
