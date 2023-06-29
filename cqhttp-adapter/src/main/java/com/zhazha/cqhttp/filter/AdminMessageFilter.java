package com.zhazha.cqhttp.filter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.command.admin.AdminCommandExecutor;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.MessageVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminMessageFilter implements MessageFilter {
    
    @Resource
    private AdminCommandExecutor adminCommandExecutor;
    
    @Override
    public Boolean match(BaseVO vo) {
        try {
            MessageVO messageVO = (MessageVO) vo;
            String raw_message = messageVO.getRaw_message();
            return StrUtil.startWithIgnoreCase(raw_message, Constants.CMD_ADMIN);
        } catch (Exception ignored) {
        }
        return false;
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        MessageVO messageVO = (MessageVO) vo;
        AdminMessage adminMessage = BeanUtil.toBean(messageVO, AdminMessage.class);
        return adminCommandExecutor.executeCommand(adminMessage);
    }
}
