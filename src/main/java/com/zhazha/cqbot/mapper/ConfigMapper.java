package com.zhazha.cqbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhazha.cqbot.bean.Config;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper extends BaseMapper<Config> {
}