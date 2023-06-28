package com.zhazha.cqbot.config;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.zhazha.cqbot.filter.AdminMessageFilter;
import com.zhazha.cqbot.filter.BlockMessageFilter;
import com.zhazha.cqbot.filter.MessageFilterChain;
import com.zhazha.cqhttp.constants.Constants;
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
    
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        return SensitiveWordBs.newInstance()
//			    .wordDeny(new MyWordDeny())
                .ignoreRepeat(false)
                .enableUrlCheck(false)
                .init();
    }
    
}
