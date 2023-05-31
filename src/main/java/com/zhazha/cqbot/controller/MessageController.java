package com.zhazha.cqbot.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zhazha.cqbot.dispatch.DispatchFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class MessageController {
	
	@Resource
	private DispatchFactory dispatchFactory;
	
	@RequestMapping("")
	public String post(@RequestBody Map<String, Object> maps, HttpServletResponse response) {
		String ret = dispatchFactory.dispatch(maps);
		if (StrUtil.isBlank(ret)) {
			response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			return "";
		}
		test(maps);
		return ret;
	}
	
	private void test(Map<String, Object> maps) {
		System.out.println("---------------------------------------------------------------");
		System.out.println(JSONUtil.toJsonStr(maps));
		System.out.println("---------------------------------------------------------------");
	}
	
}
