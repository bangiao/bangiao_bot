package com.zhazha.cqbot.filter;

import cn.hutool.core.collection.CollUtil;
import com.zhazha.cqbot.controller.vo.BaseVO;

import java.util.ArrayList;
import java.util.List;

public class MessageFilterChain {
	
	private final List<MessageFilter> filters;
	private Integer pos = 0;
	
	public MessageFilterChain() {
		this.filters = new ArrayList<>();
	}
	
	public MessageFilterChain(List<MessageFilter> filters) {
		this.filters = filters;
	}
	
	public void addFilter(MessageFilter messageFilter) {
		this.filters.add(messageFilter);
	}
	
	public void doChain(BaseVO vo, MessageFilterChain chain) {
		if (CollUtil.isEmpty(this.filters)) {
			return;
		}
		if (this.filters.size() <= this.pos) {
			return;
		}
		MessageFilter filter = this.filters.get(this.pos++);
		if (filter == null) {
			return;
		}
		filter.doFilter(vo, chain);
	}
	
}
