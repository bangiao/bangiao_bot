package com.zhazha.cqbot.filter;

import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;

public interface MessageFilter {
	Boolean match(BaseVO vo);
	
	ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception;
}
