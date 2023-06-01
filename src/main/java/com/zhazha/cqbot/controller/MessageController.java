package com.zhazha.cqbot.controller;

import cn.hutool.json.JSONUtil;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.dispatch.MessageDispatcher;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class MessageController {
	
	@Resource
	private MessageDispatcher messageDispatcher;
	
	@RequestMapping("")
	public ReplyVO post(@RequestBody Map<String, Object> maps, HttpServletRequest request, HttpServletResponse response) throws Exception {
		test(maps);
		ReplyVO vo = messageDispatcher.dispatch(maps);

		String jsonStr = JSONUtil.toJsonStr(vo);
		System.err.println(jsonStr);
		if (vo == null) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return ReplyVO.builder().build();
		}
		return vo;
	}
	
	private void test(Map<String, Object> maps) {
		System.out.println("---------------------------------------------------------------");
		System.out.println(JSONUtil.toJsonStr(maps));
		System.out.println("---------------------------------------------------------------");
	}
	
}
