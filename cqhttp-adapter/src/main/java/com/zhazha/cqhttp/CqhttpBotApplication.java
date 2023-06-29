package com.zhazha.cqhttp;

import com.zhazha.cqhttp.constants.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@EnableFeignClients
@SpringBootApplication
public class CqhttpBotApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(CqhttpBotApplication.class, args);
		ConfigurableEnvironment environment = applicationContext.getEnvironment();
		Constants.BOT_QQ = environment.getProperty("bot_qq");
		Constants.FROM_EMAIL = environment.getProperty("bot_qq") + "@qq.com";
		Constants.EMAIL_PWD = environment.getProperty("email_password");
		Constants.ADMIN_QQ = environment.getProperty("admin_qq");
		Constants.toEmail.add(environment.getProperty("to_email") + "@qq.com");
		Constants.toEmail.add(Constants.ADMIN_QQ + "@qq.com");
		Constants.AT_BOT = "[CQ:at,qq=" + Constants.BOT_QQ + "]";
	}
	
}
