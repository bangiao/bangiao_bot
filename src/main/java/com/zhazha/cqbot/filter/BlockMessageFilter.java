package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.exception.NotifyException;
import com.zhazha.cqbot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BlockMessageFilter implements MessageFilter {
	
	@Resource
	private UserService userService;
	private final String USER = "#user ";
	private final String USER_REG = USER + "register ";
	
	@Override
	public Boolean match(BaseVO vo) {
		MessageVO messageVO = (MessageVO) vo;
		if (StrUtil.startWithIgnoreCase(messageVO.getRaw_message(), USER_REG)) {
			return true;
		}
		return false;
	}
	
	@Override
	public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
		if (!match(vo)) {
			return chain.doChain(vo, chain);
		}
		MessageVO messageVO = (MessageVO) vo;
		User blockUser = userService.getBlockUser(messageVO.getUser_id());
		if (blockUser != null) {
			throw new NotifyException("请求被拦截, 你或者你申请的用户是黑名单");
		}
		return chain.doChain(vo,chain);
	}
}
