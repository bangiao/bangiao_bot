package com.zhazha.cqbot.config;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.zhazha.cqbot.filter.BlockMessageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalConfig {
	
	@Bean
	public BlockMessageFilter blockMessageFilter() {
		BlockMessageFilter messageFilter = new BlockMessageFilter();
		messageFilter.addUrl("#user register ");
		return messageFilter;
	}
	
	@Bean
	public SensitiveWordBs sensitiveWordBs() {
	    return SensitiveWordBs.newInstance()
//			    .wordDeny(new MyWordDeny())
			    .ignoreRepeat(false)
			    .init();
	}
	
}
