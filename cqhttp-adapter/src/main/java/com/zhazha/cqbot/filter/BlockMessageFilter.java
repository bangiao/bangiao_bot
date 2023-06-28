package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.constants.UserType;
import com.zhazha.cqhttp.exception.NotifyException;
import com.zhazha.cqhttp.repository.UserRepository;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.MessageVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;

import java.util.HashSet;
import java.util.Set;

public class BlockMessageFilter implements MessageFilter {
    
    @Resource
    private UserRepository userRepository;
    private final Set<String> commands = new HashSet<>();
    
    public void addUrl(String url) {
        this.commands.add(url);
    }
    
    @Override
    public Boolean match(BaseVO vo) {
        MessageVO messageVO = (MessageVO) vo;
        for (String cmd : commands) {
            if (StrUtil.containsIgnoreCase(getRawMessage(messageVO), cmd)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
        MessageVO messageVO = (MessageVO) vo;
        if (StrUtil.equalsIgnoreCase(messageVO.getUser_id().toString(), Constants.ADMIN_QQ)) {
            return chain.doChain(vo, chain);
        }
        User user = userRepository.get(messageVO.getUser_id());
        if (user == null) {
            throw new NotifyException("请求被拦截, 你没有权限");
        }
        if (StrUtil.equalsIgnoreCase(user.getType(), UserType.BLOCK.name())) {
            throw new NotifyException("请求被拦截, 你没有权限");
        }
        return chain.doChain(vo, chain);
    }
    
}
