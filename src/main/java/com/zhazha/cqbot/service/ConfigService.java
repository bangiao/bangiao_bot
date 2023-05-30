package com.zhazha.cqbot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqbot.bean.Config;
import com.zhazha.cqbot.mapper.ConfigMapper;
import org.springframework.stereotype.Service;
@Service
public class ConfigService extends ServiceImpl<ConfigMapper, Config> {

}
