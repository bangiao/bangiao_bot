package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.constants.UserType;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.exception.NotifyException;
import com.zhazha.cqbot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RegisterUserFilter implements MessageFilter {
	
	@Resource
	private UserService userService;
	private final String PRE_REGISTER = "/register ";
	private final String PRE_REGISTER_ADMIN = PRE_REGISTER + "admin ";
	private final String PRE_REGISTER_USER = PRE_REGISTER + "user ";
	
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
	public String doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
		if (!match(vo)) {
			return chain.doChain(vo, chain);
		}
		
		MessageVO messageVO = (MessageVO) vo;
		MessageVO.SenderBean sender = messageVO.getSender();
		String raw_message = messageVO.getRaw_message();
		Long userId = messageVO.getUser_id();
		
		User user = userService.getById(userId);
		if (!Constants.adminQQ.equalsIgnoreCase(userId.toString())
				|| !UserType.ADMIN.name().equalsIgnoreCase(user.getType())) {
			throw new NotifyException("你没有权限执行该申请");
		}
		
		if (StrUtil.startWithIgnoreCase(raw_message, PRE_REGISTER_USER)) {
			String qq = StrUtil.subAfter(raw_message, PRE_REGISTER_USER, false).trim();
			if (!StrUtil.isNumeric(qq)) {
				throw new NotifyException("您的格式不对: " + PRE_REGISTER_USER + "数字");
			}
			register(userId.toString(), qq, UserType.USER.name());
		} else if (StrUtil.startWithIgnoreCase(raw_message, PRE_REGISTER_ADMIN)) {
			String qq = StrUtil.subAfter(raw_message, PRE_REGISTER_USER, false).trim();
			if (!StrUtil.isNumeric(qq)) {
				throw new NotifyException("您的格式不对: " + PRE_REGISTER_USER + "数字");
			}
			register(userId.toString(), qq, UserType.ADMIN.name());
		}
		ReplyVO replyVO = ReplyVO.builder()
				.auto_escape(true)
				.reply("注册成功")
				.at_sender(true)
				.build();
		return JSONUtil.toJsonStr(replyVO);
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
		} else {
			throw new NotifyException("该用户已有权限");
		}
	}
}
