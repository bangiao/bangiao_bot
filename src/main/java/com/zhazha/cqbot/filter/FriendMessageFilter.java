package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.chat.ChatExecutor;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FriendMessageFilter implements MessageFilter {
	@Resource
	private ChatExecutor chatExecutor;
	
	@Override
	public Boolean match(BaseVO vo) {
		try {
			MessageVO messageVO = (MessageVO) vo;
			String raw_message = messageVO.getRaw_message();
			if (StrUtil.startWithIgnoreCase(raw_message, Constants.CHAT)) {
				return true;
			}
		} catch (Exception ignored) {
		}
		return false;
	}
	
	@Override
	public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
		MessageVO messageVO = (MessageVO) vo;
		String response = chatExecutor.execute(messageVO);
		return ReplyVO.builder().reply(response).at_sender(true)
				.auto_escape(false).build();
	}
}
