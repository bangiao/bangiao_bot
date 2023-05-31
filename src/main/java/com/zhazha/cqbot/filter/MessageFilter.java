package com.zhazha.cqbot.filter;

import com.zhazha.cqbot.controller.vo.BaseVO;

public interface MessageFilter {
	Boolean match(BaseVO vo);
	
	String doFilter(BaseVO vo, MessageFilterChain chain) throws Exception;
}
