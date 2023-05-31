package com.zhazha.cqbot.utils;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.remote.msg.RMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class SendMessageUtils {
    
    @Resource
    private RMessageService rMessageService;
    
    public void sendMessage(Long userId, String content, Boolean autoEscape) {
        if (userId == null || StrUtil.isBlank(content)
                || autoEscape == null) {
            log.error("发送消息时, 参数为空");
            return;
        }
        rMessageService.sendMessage(userId, content, autoEscape);
    }
    
    public void sendGroupMessage(Long groupId, String content, Boolean autoEscape) {
        if (groupId == null || StrUtil.isBlank(content)
                || autoEscape == null) {
            log.error("发送消息时, 参数为空");
            return;
        }
        rMessageService.sendGroupMsg(groupId, content, autoEscape);
    }
    
}
