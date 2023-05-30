package com.zhazha.cqbot.runner;

import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.request.ChatRequest;
import com.zhazha.cqbot.utils.CQCodeUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ChatExecutor {
	
	@Resource
	private ChatRequest chatRequest;
	
	public String execute(MessageVO vo) {
		String message = vo.getRaw_message();
		String question = CQCodeUtils.getText(message);
		return chatRequest.request(vo.getSender().getUser_id(), question);
	}
	
}
