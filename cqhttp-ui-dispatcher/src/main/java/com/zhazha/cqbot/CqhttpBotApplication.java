package com.zhazha.cqbot;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.zhazha.cqhttp.constants.Constants;
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
		Constants.BOT_QQ = environment.getProperty("bot_qq");
		Constants.EMAIL_PWD = environment.getProperty("email_password");
		Constants.ADMIN_QQ = environment.getProperty("admin_qq");
		Constants.toEmail.add(environment.getProperty("to_email"));
	}
	
}
