package com.zhazha.cqbot.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskUtils {
	
	private static final BlockingQueue<Runnable> workQueue = new PriorityBlockingQueue<>(1024);
	
	private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 300,
			3, TimeUnit.SECONDS, workQueue,
			new ThreadPoolExecutor.DiscardOldestPolicy());
	
	public static void addTask(Runnable task) {
		executor.execute(task);
	}
	
}
