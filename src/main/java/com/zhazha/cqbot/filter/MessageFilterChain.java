package com.zhazha.cqbot.filter;

import cn.hutool.core.collection.CollUtil;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.exception.NotifyException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Scope("prototype")
@Component
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
	
	public String doChain(BaseVO vo, MessageFilterChain chain) throws Exception, NotifyException {
		if (CollUtil.isEmpty(this.filters)) {
			return null;
		}
		if (this.filters.size() <= this.pos) {
			return null;
		}
		MessageFilter filter = this.filters.get(this.pos++);
		if (filter == null) {
			return null;
		}
		return filter.doFilter(vo, chain);
	}
	
}
