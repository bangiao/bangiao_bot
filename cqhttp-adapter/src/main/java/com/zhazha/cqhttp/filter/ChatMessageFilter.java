package com.zhazha.cqhttp.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.command.chat.ChatCommandExecutor;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.MessageVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatMessageFilter implements MessageFilter {
    @Resource
    private ChatCommandExecutor chatCommandExecutor;
    
    @Override
    public Boolean match(BaseVO vo) {
        // 强调只能在私聊找执行该指令
        MessageVO messageVO = (MessageVO) vo;
        return StrUtil.startWithIgnoreCase(messageVO.getRaw_message(), Constants.CMD_CHAT);
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        AdminMessage messageVO = (AdminMessage) vo;
        return chatCommandExecutor.executeCommand(messageVO);
    }
}
