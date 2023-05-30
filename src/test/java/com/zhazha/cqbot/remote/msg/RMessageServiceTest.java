package com.zhazha.cqbot.remote.msg;

import com.zhazha.cqbot.remote.msg.result.GetMsgResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@SpringBootTest
class RMessageServiceTest {
	
	@Resource
	private RMessageService rMessageService;
	
	@BeforeEach
	void setUp() {
	}
	
	@AfterEach
	void tearDown() {
	}
	
	@Test
	void sendMessage() {
		rMessageService.sendMessage(2033445917L, null, "hehe", false);
	}
	
	@Test
	void getMsg() {
		GetMsgResult msg = rMessageService.getMsg(-1320528546);
		System.err.println(msg);
	}
	
	@Test
	void deleteMsg() {
	}
	
	@Test
	void getForwardMsg() {
	}
}