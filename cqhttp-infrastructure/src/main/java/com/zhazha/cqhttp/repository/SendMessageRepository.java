package com.zhazha.cqhttp.repository;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.remote.msg.RMessageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class SendMessageRepository {
    
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
