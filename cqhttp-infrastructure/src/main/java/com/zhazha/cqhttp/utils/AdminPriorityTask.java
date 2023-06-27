package com.zhazha.cqhttp.utils;

public class AdminPriorityTask extends PriorityTask {
	public AdminPriorityTask(Runnable task) {
		super(0, task);
	}
}
