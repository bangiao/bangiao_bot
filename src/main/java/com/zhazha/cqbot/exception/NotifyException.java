package com.zhazha.cqbot.exception;

public class NotifyException extends RuntimeException {
	
	private final Long user_id;
	private final Long group_id;
	
	public NotifyException(Long user_id, Long group_id, String message) {
		super(message);
		this.group_id = group_id;
		this.user_id = user_id;
	}
	
	public Long getUser_id() {
		return user_id;
	}
	
	public Long getGroup_id() {
		return group_id;
	}
}
