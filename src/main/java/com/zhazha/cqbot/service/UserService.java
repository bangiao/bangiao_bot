package com.zhazha.cqbot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.UserType;
import com.zhazha.cqbot.mapper.UserMapper;
import org.springframework.stereotype.Service;
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
	
	public User getBlockUser(Long user_id) {
		return lambdaQuery().eq(User::getQq, user_id)
				.eq(User::getType, UserType.BLOCK.name())
				.one();
	}
	
}
