package com.zhazha.cqbot.dispatch;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.constants.UserType;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.exception.BlockException;
import com.zhazha.cqbot.filter.FriendMessageFilter;
import com.zhazha.cqbot.filter.MessageFilterChain;
import com.zhazha.cqbot.filter.RegisterUserFilter;
import com.zhazha.cqbot.remote.msg.RMessageService;
import com.zhazha.cqbot.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class FriendDispatcher {
	
	@Resource
	private FriendMessageFilter friendMessageFilter;
	@Resource
	private RegisterUserFilter registerUserFilter;
	@Resource
	private RMessageService rMessageService;
	@Resource
	private UserService userService;
	
	public String dispatch(MessageVO vo) {
		try {
			User one = userService.lambdaQuery().eq(User::getQq, vo.getUser_id())
					.one();
			
			if (one != null) {
				if (StrUtil.equalsIgnoreCase(one.getType(), UserType.BLOCK.name())) {
					throw new BlockException("抱歉!!! 您没有权限, 请告知管理员");
				}
			}
			MessageFilterChain messageFilterChain = SpringUtil.getBean(MessageFilterChain.class);
			messageFilterChain.addFilter(registerUserFilter);
			messageFilterChain.addFilter(friendMessageFilter);
			return messageFilterChain.doChain(vo, messageFilterChain);
		} catch (Exception e) {
			rMessageService.sendMessage(Long.valueOf(Constants.adminQQ), null, e.getMessage(), true);
		}
		return null;
	}
}
