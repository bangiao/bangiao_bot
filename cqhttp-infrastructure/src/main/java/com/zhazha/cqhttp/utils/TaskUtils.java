package com.zhazha.cqhttp.utils;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TaskUtils {
	
	private static final BlockingQueue<Runnable> workQueue = new PriorityBlockingQueue<>(1024);
	
	private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 300,
			3, TimeUnit.SECONDS, workQueue,
			new ThreadPoolExecutor.DiscardOldestPolicy());
	
	public void addTask(Runnable task) {
		try {
			executor.execute(task);
		} catch (Exception e) {
			System.err.println("出现异常了");
		}
	}
	
}
