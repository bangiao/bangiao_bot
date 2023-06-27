package com.zhazha.cqhttp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhazha.cqhttp.bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}