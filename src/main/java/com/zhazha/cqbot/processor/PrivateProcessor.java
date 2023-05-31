package com.zhazha.cqbot.processor;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.UserType;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.exception.BlockException;
import com.zhazha.cqbot.exception.NotifyException;
import com.zhazha.cqbot.filter.AuthMessageFilter;
import com.zhazha.cqbot.filter.FriendMessageFilter;
import com.zhazha.cqbot.filter.MessageFilterChain;
import com.zhazha.cqbot.service.UserService;
import com.zhazha.cqbot.utils.AdminPriorityTask;
import com.zhazha.cqbot.utils.TaskUtils;
import com.zhazha.cqbot.utils.UserPriorityTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

@Component
public class PrivateProcessor {
	
	@Resource
	private UserService userService;
	@Resource
	private FriendMessageFilter friendMessageFilter;
	@Resource
	private AuthMessageFilter authMessageFilter;
	
	public void runner(MessageVO vo) {
		// 判断下是不是黑名单
		MessageVO.SenderBean sender = vo.getSender();
		Long userId = sender.getUser_id();
		Optional<User> opt = userService.lambdaQuery()
				.eq(User::getQq, userId)
				.oneOpt();
		if (opt.isEmpty()) {
			throw new NotifyException(vo.getUser_id(), vo.getGroup_id(), "你没有开通权限, 请跟管理员申请");
		}
		if (StrUtil.equalsIgnoreCase(opt.get().getType(), UserType.BLOCK.name())) {
			throw new BlockException("您是尊贵的黑名单用户");
		}
		if (StrUtil.equalsIgnoreCase(opt.get().getType(), UserType.ADMIN.name())) {
			TaskUtils.addTask(new AdminPriorityTask(() -> {
				execute(vo);
			}));
			throw new NotifyException("尊贵用户您好, 您的问题是: " + vo.getRaw_message() + " 已为您优先安排任务");
//			throw new NotifyException(vo.getUser_id(), vo.getGroup_id(), "尊贵用户您好, 您的问题是: " + vo.getRaw_message() + " 已为您优先安排任务");
		}
		if (StrUtil.equalsIgnoreCase(opt.get().getType(), UserType.USER.name())) {
			TaskUtils.addTask(new UserPriorityTask(() -> {
				execute(vo);
			}));
			throw new NotifyException("你好, 你的问题是: " + vo.getRaw_message() + " 已为你安排任务");
//			throw new NotifyException(vo.getUser_id(), vo.getGroup_id(), "你好, 你的问题是: " + vo.getRaw_message() + " 已为你安排任务");
		}
	}
	
	
	private void execute(MessageVO vo) {
		MessageFilterChain messageFilterChain = new MessageFilterChain();
		messageFilterChain.addFilter(authMessageFilter);
		messageFilterChain.addFilter(friendMessageFilter);
		messageFilterChain.doChain(vo,messageFilterChain);
	}
}