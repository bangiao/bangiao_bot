package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.bean.User;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.constants.UserType;
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
    private final Set<String> commands = new HashSet<>();
    
    public void addUrl(String url) {
        this.commands.add(url);
    }
    
    @Override
    public Boolean match(BaseVO vo) {
        MessageVO messageVO = (MessageVO) vo;
        for (String cmd : commands) {
            if (StrUtil.containsIgnoreCase(messageVO.getRaw_message(), cmd)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
        MessageVO messageVO = (MessageVO) vo;
        if (StrUtil.equalsIgnoreCase(messageVO.getUser_id().toString(), Constants.adminQQ)) {
            return chain.doChain(vo, chain);
        }
        User user = userService.getUser(messageVO.getUser_id());
        if (user == null) {
            throw new NotifyException("请求被拦截, 你没有权限");
        }
        if (StrUtil.equalsIgnoreCase(user.getType(), UserType.BLOCK.name())) {
            throw new NotifyException("请求被拦截, 你没有权限");
        }
        return chain.doChain(vo, chain);
    }
    
}
