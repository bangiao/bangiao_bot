package com.zhazha.cqbot.controller;

import cn.hutool.json.JSONUtil;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.dispatch.MessageDispatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class MessageController {
	
	@Resource
	private MessageDispatcher messageDispatcher;
	
	@RequestMapping("")
	public ReplyVO post(@RequestBody Map<String, Object> maps, HttpServletResponse response) throws Exception {
		ReplyVO vo = messageDispatcher.dispatch(maps);
		if (vo == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return ReplyVO.builder().build();
		}
		test(maps);
		return vo;
	}
	
	private void test(Map<String, Object> maps) {
		System.out.println("---------------------------------------------------------------");
		System.out.println(JSONUtil.toJsonStr(maps));
		System.out.println("---------------------------------------------------------------");
	}
	
}
