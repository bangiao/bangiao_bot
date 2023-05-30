package com.zhazha.cqbot;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.zhazha.cqbot.constants.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableFeignClients
@EnableSpringUtil
public class CqhttpBotApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(CqhttpBotApplication.class, args);
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		Constants.emailPwd = environment.getProperty("email_password");
	}
	
}
