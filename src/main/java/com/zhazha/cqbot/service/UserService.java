package com.zhazha.cqbot.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.UserType;
import com.zhazha.cqbot.mapper.UserMapper;
import org.springframework.stereotype.Service;
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
	
	public User getBlockUser(Long userId) {
		if (null == userId) {
			return null;
		}
		return lambdaQuery().eq(User::getQq, userId)
				.eq(User::getType, UserType.BLOCK.name())
				.one();
	}
	
	public User getByQQ(Long userId) {
		return getUser(userId);
	}
	
	public User getByQQ(String userId) {
		return getUser(userId);
	}
	
	public User getUser(Long userId) {
		if (null == userId) {
			return null;
		}
		return getById(userId);
	}
	
	public User getUser(String userId) {
		if (StrUtil.isBlank(userId)) {
			return null;
		}
		return getById(userId);
	}
	
}
