package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.exception.NotifyException;
import com.zhazha.cqbot.service.UserService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

public class BlockMessageFilter implements MessageFilter {
    
    @Resource
    private UserService userService;
    private final Set<String> urls = new HashSet<>();
    
    public void addUrl(String url) {
        this.urls.add(url);
    }
    
    @Override
    public Boolean match(BaseVO vo) {
        MessageVO messageVO = (MessageVO) vo;
        for (String url : urls) {
            if (StrUtil.containsIgnoreCase(messageVO.getRaw_message(), url)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
        MessageVO messageVO = (MessageVO) vo;
        User blockUser = userService.getBlockUser(messageVO.getUser_id());
        if (blockUser != null) {
            throw new NotifyException("请求被拦截, 你或者你申请的用户是黑名单");
        }
        return chain.doChain(vo, chain);
    }
}
