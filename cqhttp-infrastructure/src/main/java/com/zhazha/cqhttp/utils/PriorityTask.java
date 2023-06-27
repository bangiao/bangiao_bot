package com.zhazha.cqhttp.utils;

public abstract class PriorityTask implements Runnable, Comparable<PriorityTask> {
	private Integer priority;
	private Runnable task;
	
	public PriorityTask(int priority, Runnable task) {
		this.priority = priority;
		this.task = task;
	}
	
	@Override
	public int compareTo(PriorityTask o) {
		return Integer.compare(priority, o.priority);
	}
	
	@Override
	public void run() {
		task.run();
	}
}
