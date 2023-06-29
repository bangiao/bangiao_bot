package com.zhazha.cqhttp.controller;

import com.zhazha.cqhttp.dispatch.MessageDispatcher;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MessageController {
	
	@Resource
	private MessageDispatcher messageDispatcher;
	
	@RequestMapping("/")
	public ReplyVO post(@RequestBody Map<String, Object> maps, HttpServletResponse response) throws Exception {
		ReplyVO vo = messageDispatcher.dispatch(maps);
		if (vo == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return ReplyVO.builder().build();
		}
		return vo;
	}
	
}
