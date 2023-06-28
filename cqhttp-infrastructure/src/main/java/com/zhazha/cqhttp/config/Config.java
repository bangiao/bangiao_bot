package com.zhazha.cqhttp.config;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
public class Config {
    
    @Bean
    public SensitiveWordBs sensitiveWordBs() {
        return SensitiveWordBs.newInstance()
//			    .wordDeny(new MyWordDeny())
                .ignoreRepeat(false)
                .enableUrlCheck(false)
                .init();
    }
    
}
