package com.zhazha.cqbot.controller;

import cn.hutool.json.JSONUtil;
import com.zhazha.cqbot.processor.ProcessorFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class EventController {
	
	@Resource
	private ProcessorFactory processorFactory;
	
	@RequestMapping("")
	public String post(@RequestBody Map<String, Object> maps) {
		processorFactory.load(maps);
		test(maps);
		return "";
	}
	
	private void test(Map<String, Object> maps) {
		System.out.println("---------------------------------------------------------------");
		System.out.println(JSONUtil.toJsonStr(maps));
		System.out.println("---------------------------------------------------------------");
	}
	
}
