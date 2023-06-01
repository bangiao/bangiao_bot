package com.zhazha.cqbot.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.runner.ChatExecutor;
import com.zhazha.cqbot.utils.CQCodeUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 进入这里的请求, 默认就是群消息
 */
@Component
public class AtGroupMessageFilter implements MessageFilter {
	
	@Resource
	private ChatExecutor chatExecutor;
	
	@Override
	public Boolean match(BaseVO vo) {
		MessageVO messageVO = (MessageVO) vo;
		if (null == messageVO) {
			return false;
		}
		if (!StrUtil.equalsIgnoreCase(messageVO.getMessage_type(), "group")) {
			return false;
		}
		String raw_message = messageVO.getRaw_message();
		Set<String> at = CQCodeUtils.getAt(raw_message);
		return CollUtil.isNotEmpty(at);
	}
	
	@Override
	public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
		if (!match(vo)) {
			chain.doChain(vo, chain);
			return null;
		}
		MessageVO messageVO = (MessageVO) vo;
		
		Set<String> at = CQCodeUtils.getAt(messageVO.getRaw_message());
		String response = chatExecutor.execute(messageVO);
		// [CQ:at,qq=222222] [CQ:at,qq=11111] 开始
		StringBuilder cqBuilder = new StringBuilder();
		at.forEach(qq -> cqBuilder.append("[CQ:at,qq=").append(qq).append("] "));
		cqBuilder.append(response);
		return ReplyVO.builder().reply(cqBuilder.toString()).at_sender(true).build();
	}
}
