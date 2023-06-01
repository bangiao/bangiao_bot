package com.zhazha.cqbot.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.UserType;
import com.zhazha.cqbot.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    
    /**
     * 保存或者更新 bot
     *
     * @param user
     */
    @Transactional
    public void saveOrUpdateBot(User user) {
        if (null == user) {
            return;
        }
        User bot = lambdaQuery().eq(User::getType, UserType.BOT.name()).one();
        if (null == bot) {
            // 没有机器人
            user.setType(UserType.BOT.name());
        } else {
            CopyOptions options = CopyOptions.create();
            options.ignoreNullValue();
            BeanUtil.copyProperties(user, bot, options);
            user = bot;
        }
        saveOrUpdate(user);
    }
    
    /**
     * @return 当前机器人
     */
    public User getCurBot() {
        return lambdaQuery().eq(User::getType, UserType.BOT.name()).one();
    }
    
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
