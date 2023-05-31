package com.zhazha.cqbot.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class SendMessageUtils {
    
    @Resource
    private SendMessageUtils sendMessageUtils;
    
    public void sendMessage(Long userId, String content, Boolean autoEscape) {
        if (userId == null || StrUtil.isBlank(content)
                || autoEscape == null) {
            log.error("发送消息时, 参数为空");
            return;
        }
        sendMessageUtils.sendMessage(userId, content, autoEscape);
    }
    
    public void sendGroupMessage(Long groupId, String content, Boolean autoEscape) {
        if (groupId == null || StrUtil.isBlank(content)
                || autoEscape == null) {
            log.error("发送消息时, 参数为空");
            return;
        }
        sendMessageUtils.sendGroupMessage(groupId, content, autoEscape);
    }
    
}
