package com.zhazha.cqhttp.exception;

import com.alibaba.cola.exception.BizException;

public class ChatException extends BizException {
	public ChatException(String message) {
		super(message);
	}
}
