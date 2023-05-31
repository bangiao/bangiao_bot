package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.remote.msg.RMessageService;
import com.zhazha.cqbot.runner.ChatExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 这个的优先级应该最后的, 因为 match 不太精确
 */
@Component
public class FriendMessageFilter implements MessageFilter {
	@Resource
	private ChatExecutor chatExecutor;
	@Resource
	private RMessageService rMessageService;
	
	@Override
	public Boolean match(BaseVO vo) {
		try {
			MessageVO messageVO = (MessageVO) vo;
			return StrUtil.equalsIgnoreCase(messageVO.getMessage_type(), "private");
		} catch (Exception ignored) {
		}
		return false;
	}
	
	@Override
	public void doFilter(BaseVO vo, MessageFilterChain chain) {
		if (!match(vo)) {
			chain.doChain(vo, chain);
			return;
		}
		MessageVO messageVO = (MessageVO) vo;
		String response = chatExecutor.execute(messageVO);
		rMessageService.sendMessage(messageVO.getSender().getUser_id(), messageVO.getGroup_id(),
				response, false);
	}
}
