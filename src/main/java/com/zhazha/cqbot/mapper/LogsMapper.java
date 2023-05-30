package com.zhazha.cqbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhazha.cqbot.bean.Logs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogsMapper extends BaseMapper<Logs> {
}