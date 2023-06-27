package com.zhazha.cqhttp.utils;

public class UserPriorityTask extends PriorityTask {
	public UserPriorityTask(Runnable task) {
		super(10, task);
	}
}
