package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.chat.ChatEngine;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.utils.CQCodeUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 是不是 at 机器人?
 * 有没有权限?
 */
@Component
public class AtGroupMessageFilter implements MessageFilter {
	
	@Resource
	private ChatEngine chatEngine;
	
	@Override
	public Boolean match(BaseVO vo) {
		MessageVO messageVO = (MessageVO) vo;
		if (null == messageVO) {
			return false;
		}
		String raw_message = messageVO.getRaw_message();
		return StrUtil.containsIgnoreCase(raw_message, Constants.AT_BOT);
	}
	
	@Override
	public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
		MessageVO messageVO = (MessageVO) vo;
		Set<String> at = CQCodeUtils.getAtWithout(messageVO.getRaw_message());
		String response = chatEngine.execute(messageVO);
		// [CQ:at,qq=222222] [CQ:at,qq=11111] 开始
		StringBuilder cqBuilder = new StringBuilder();
		at.forEach(qq -> cqBuilder.append("[CQ:at,qq=").append(qq).append("] "));
		cqBuilder.append(response);
		return ReplyVO.builder().reply(cqBuilder.toString()).at_sender(true).build();
	}
}
