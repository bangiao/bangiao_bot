package com.zhazha.cqbot.config;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {
	
	@Bean
	public SensitiveWordBs sensitiveWordBs() {
	    return SensitiveWordBs.newInstance()
//			    .wordDeny(new MyWordDeny())
			    .ignoreRepeat(false)
			    .init();
	}
	
}
