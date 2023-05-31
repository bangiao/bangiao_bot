package com.zhazha.cqbot.filter;

import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AuthMessageFilterTest {
	
	@BeforeEach
	void setUp() {
	}
	
	@AfterEach
	void tearDown() {
	}
	
	@Test
	void match() {
	}
	
	@Resource
	private UserService userService;
	
	@Test
	void doFilter() {
		User user = userService.lambdaQuery().eq(User::getQq, 2033445917).one();
		System.err.println(user);
		user = userService.lambdaQuery().eq(User::getQq, 2033445918).one();
		System.err.println(user);
	}
}