package com.zhazha.cqbot.dispatch;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.filter.FilterChainManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class MessageDispatcher {
    
    @Resource
    private FilterChainManager filterChainManager;
    
    public ReplyVO dispatch(Map<String, Object> maps) throws Exception {
//		message, message_sent, request, notice, meta_event
        String post_type = (String) maps.get("post_type");
        if (StrUtil.isBlank(post_type)) {
            throw new RuntimeException("消息类型不对");
        }
        switch (post_type) {
            case "meta_event": {
                // 这种类型的消息不用处理
                return null;
            }
            case "message": {
                MessageVO messageVO = BeanUtil.copyProperties(maps,
                        MessageVO.class, "");
                return filterChainManager.createMessageFilterChain(messageVO);
            }
            // TODO: 2023/5/30 后续还有很多别事件类型
        }
        return null;
    }
}
