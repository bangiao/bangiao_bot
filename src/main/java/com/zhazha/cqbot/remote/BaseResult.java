package com.zhazha.cqbot.remote;

import lombok.Data;

@Data
public class BaseResult {
	private Long retcode;
	private String status;
	private String message;
}
