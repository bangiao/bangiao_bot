package com.zhazha.cqbot.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqbot.bean.Config;
import com.zhazha.cqbot.constants.ConfigType;
import com.zhazha.cqbot.mapper.ConfigMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService extends ServiceImpl<ConfigMapper, Config> {
    
    public List<Config> listByQQ(Long qq) {
        if (null == qq) {
            return null;
        }
        return lambdaQuery()
                .eq(Config::getValue3, qq)
                .list();
    }
    
    public List<Config> listByQQ(String qq) {
        if (StrUtil.isBlank(qq)) {
            return null;
        }
        return lambdaQuery()
                .eq(Config::getValue3, qq)
                .list();
    }
    
    public List<Config> listChatByQQ(Long qq) {
        if (null == qq) {
            return null;
        }
        return lambdaQuery()
                .eq(Config::getValue3, qq)
                .eq(Config::getType,ConfigType.CHATGPT)
                .list();
    }
    
    public List<Config> listChatByQQ(String qq) {
        if (StrUtil.isBlank(qq)) {
            return null;
        }
        return lambdaQuery()
                .eq(Config::getValue3, qq)
                .eq(Config::getType,ConfigType.CHATGPT)
                .list();
    }
    
    public List<Config> listChat(String qq) {
        if (StrUtil.isBlank(qq)) {
            return null;
        }
        return lambdaQuery()
                .eq(Config::getType,ConfigType.CHATGPT)
                .list();
    }
    
    public Config curNode() {
        return lambdaQuery()
                .eq(Config::getType, ConfigType.CHATGPT.name())
                .eq(Config::getStatus, 1)
                .one();
    }
    
}
