package com.zhazha.cqbot.controller;

import cn.hutool.json.JSONUtil;
import com.zhazha.cqbot.processor.ProcessorFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class EventController {
	
	@Resource
	private ProcessorFactory processorFactory;


	@RequestMapping("")
	public void post(@RequestBody Map<String, Object> maps, HttpServletResponse response) {
		test(maps, response);
		processorFactory.load(maps);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	private void test(Map<String, Object> maps, HttpServletResponse response) {
		System.out.println("---------------------------------------------------------------");
		System.out.println(JSONUtil.toJsonStr(maps));
		System.out.println("---------------------------------------------------------------");
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
}
