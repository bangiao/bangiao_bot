package com.zhazha.cqbot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.mapper.UserMapper;
import org.springframework.stereotype.Service;
@Service
public class UserService extends ServiceImpl<UserMapper, User> {

}
