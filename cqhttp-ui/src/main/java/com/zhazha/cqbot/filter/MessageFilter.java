package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.MessageVO;
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
	
	default String getRawMessage(MessageVO vo) {
		return StrUtil.trimStart(vo.getRaw_message());
	}
}