package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.remote.msg.RMessageService;
import com.zhazha.cqbot.runner.ChatExecutor;
import com.zhazha.cqbot.utils.CQCodeUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 进入这里的请求, 默认就是群消息
 */
@Component
public class AtGroupMessageFilter implements MessageFilter {
	
	@Resource
	private ChatExecutor chatExecutor;
	
	@Resource
	private RMessageService rMessageService;
	
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
		return StrUtil.startWithIgnoreCase(raw_message, "/q ");
	}
	
	@Override
	public void doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
		if (!match(vo)) {
			chain.doChain(vo, chain);
			return;
		}
		MessageVO messageVO = (MessageVO) vo;
		if (null == messageVO) {
			chain.doChain(vo, chain);
			return;
		}
		
		List<String> at = CQCodeUtils.getAt(messageVO.getRaw_message());
		String response = chatExecutor.execute(messageVO);
		// [CQ:at,qq=222222] [CQ:at,qq=11111] 开始
		StringBuilder cqBuilder = new StringBuilder();
		at.forEach(qq -> cqBuilder.append("[CQ:at,qq=").append(qq).append("] "));
		cqBuilder.append(response);
		rMessageService.sendGroupMsg(((MessageVO) vo).getGroup_id(),
				cqBuilder.toString(), true);
		chain.doChain(vo, chain);
	}
}
