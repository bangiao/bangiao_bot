package com.zhazha.cqbot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhazha.cqbot.bean.Words;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WordsMapper extends BaseMapper<Words> {
}