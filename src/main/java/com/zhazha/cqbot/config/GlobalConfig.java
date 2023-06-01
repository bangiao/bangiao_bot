package com.zhazha.cqbot.config;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.filter.BlockMessageFilter;
import com.zhazha.cqbot.filter.MessageFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class GlobalConfig {
    
    @Bean
    public BlockMessageFilter blockMessageFilter() {
        BlockMessageFilter messageFilter = new BlockMessageFilter();
        messageFilter.addUrl("#user register ");
        messageFilter.addUrl(Constants.AT_BOT);
        messageFilter.addUrl(Constants.CMD_CHAT);
        return messageFilter;
    }
    
    @Scope("prototype")
    @Bean
    public MessageFilterChain messageFilterChain() {
        MessageFilterChain messageFilterChain = new MessageFilterChain();
        messageFilterChain.addFilter(blockMessageFilter());
        return messageFilterChain;
    }
    
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        return SensitiveWordBs.newInstance()
//			    .wordDeny(new MyWordDeny())
                .ignoreRepeat(false)
                .init();
    }
    
}
