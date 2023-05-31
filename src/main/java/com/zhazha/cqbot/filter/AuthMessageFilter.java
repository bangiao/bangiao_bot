package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.constants.UserType;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.exception.BlockException;
import com.zhazha.cqbot.exception.NotifyException;
import com.zhazha.cqbot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AuthMessageFilter implements MessageFilter {
	
	@Resource
	private UserService userService;
	private final String PRE_REGISTER = "/register ";
	private final String PRE_REGISTER_ADMIN = "/register admin ";
	private final String PRE_REGISTER_USER = "/register user ";
	
	@Override
	public Boolean match(BaseVO vo) {
		try {
			MessageVO messageVO = (MessageVO) vo;
			String raw_message = messageVO.getRaw_message();
			return StrUtil.startWithIgnoreCase(raw_message, PRE_REGISTER);
		} catch (Exception ignored) {
		}
		return false;
	}
	
	@Override
	public void doFilter(BaseVO vo, MessageFilterChain chain) {
		if (!match(vo)) {
			chain.doChain(vo,chain);
			return;
		}
		MessageVO messageVO = (MessageVO) vo;
		MessageVO.SenderBean sender = messageVO.getSender();
		
		// 注册
		String raw_message = messageVO.getRaw_message();
		
		String qq = StrUtil.subAfter(raw_message, PRE_REGISTER_USER, false).trim();
		if (!StrUtil.isNumeric(qq)) {
			throw new NotifyException(messageVO.getSender().getUser_id(),
					messageVO.getGroup_id(), "您的格式不对: " + PRE_REGISTER_USER + "数字");
		}
		
		if (StrUtil.startWithIgnoreCase(raw_message, PRE_REGISTER_USER)) {
			// 申请人是否有权限?
			Long userId = sender.getUser_id();
			User byId = userService.getById(userId);
			if (!UserType.ADMIN.name().equalsIgnoreCase(byId.getType())) {
				throw new NotifyException("你没有权限执行该申请");
			}
			register(sender.getUser_id().toString(), qq, UserType.USER.name());
		} else if (StrUtil.startWithIgnoreCase(raw_message, PRE_REGISTER_ADMIN) &&
				StrUtil.equalsIgnoreCase(sender.getUser_id().toString(), Constants.adminQQ)) {
			register(sender.getUser_id().toString(), qq, UserType.ADMIN.name());
		}
	}
	
	// register user 2222222222
	private void register(String senderId, String qq, String type) {
		User one = userService.lambdaQuery().eq(User::getQq, qq)
				.one();
		
		if (null == one) {
			User user = User.builder()
					.qq(qq)
					.createQq(senderId)
					.type(type)
					.build();
			userService.saveOrUpdate(user);
		} else if (StrUtil.equalsIgnoreCase(one.getType(), UserType.BLOCK.name())) {
			throw new BlockException("黑名单用户, 需要先解封");
		} else {
			throw new NotifyException("该用户已有权限");
		}
	}
}
