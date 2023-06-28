package com.zhazha.cqhttp.filter;

import cn.hutool.core.collection.CollUtil;
import com.zhazha.cqhttp.exception.NotifyException;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.ReplyVO;

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
	
	public ReplyVO doChain(BaseVO vo, MessageFilterChain chain) throws Exception, NotifyException {
		if (CollUtil.isEmpty(this.filters)) {
			return ReplyVO.builder()
					.at_sender(true)
					.reply("请联系管理员配置功能")
					.build();
		}
		if (this.filters.size() <= this.pos) {
			return ReplyVO.builder()
					.at_sender(true)
					.reply("没有匹配上指令")
					.build();
		}
		MessageFilter filter = this.filters.get(this.pos++);
		if (filter == null) {
			return ReplyVO.builder()
					.at_sender(true)
					.reply("过滤器配置错误, 请联系管理员")
					.build();
		}
		return filter.filter(vo, chain);
	}
	
}
