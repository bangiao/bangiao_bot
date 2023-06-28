package com.zhazha.cqhttp.config;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.filter.AdminMessageFilter;
import com.zhazha.cqhttp.filter.BlockMessageFilter;
import com.zhazha.cqhttp.filter.MessageFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableSpringUtil
public class GlobalConfig {
    
    @Bean
    public AdminMessageFilter adminMessageFilter() {
        return new AdminMessageFilter();
    }
    
    @Bean
    public BlockMessageFilter blockMessageFilter() {
        BlockMessageFilter messageFilter = new BlockMessageFilter();
        messageFilter.addUrl(Constants.CMD_USER);
        messageFilter.addUrl(Constants.AT_BOT);
        messageFilter.addUrl(Constants.CMD_CHAT);
        return messageFilter;
    }
    
    @Scope("prototype")
    @Bean
    public MessageFilterChain messageFilterChain() {
        MessageFilterChain messageFilterChain = new MessageFilterChain();
        messageFilterChain.addFilter(adminMessageFilter());
        messageFilterChain.addFilter(blockMessageFilter());
        return messageFilterChain;
    }
}
