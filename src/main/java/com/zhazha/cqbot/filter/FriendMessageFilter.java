package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.runner.ChatExecutor;
import com.zhazha.cqbot.utils.SendMessageUtils;
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
	private SendMessageUtils sendMessageUtils;
	
	@Override
	public Boolean match(BaseVO vo) {
		try {
			MessageVO messageVO = (MessageVO) vo;
			String raw_message = messageVO.getRaw_message();
			if (StrUtil.startWithIgnoreCase(raw_message, "## ")) {
				return true;
			}
		} catch (Exception ignored) {
		}
		return false;
	}
	
	@Override
	public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
		if (!match(vo)) {
			return chain.doChain(vo, chain);
		}
		MessageVO messageVO = (MessageVO) vo;
		String response = chatExecutor.execute(messageVO);
		sendMessageUtils.sendMessage(messageVO.getSender().getUser_id(),
				response, false);
		return ReplyVO.builder().reply(response).at_sender(true).auto_escape(false).build();
	}
}
