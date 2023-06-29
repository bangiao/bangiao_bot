package com.zhazha.cqhttp.filter;

import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.ReplyVO;

public interface MessageFilter {
	Boolean match(BaseVO vo);
	
	ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception;
	
	default ReplyVO filter(BaseVO vo, MessageFilterChain chain) throws Exception {
		if (!match(vo)) {
			return chain.doChain(vo, chain);
		}
		return doFilter(vo, chain);
	}
	
}
