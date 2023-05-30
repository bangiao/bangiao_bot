package com.zhazha.cqbot.processor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.controller.vo.MessageVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class ProcessorFactory {
	
	@Resource
	private PrivateProcessor privateProcessor;
	
	public void load(Map<String, Object> maps) {
//		message, message_sent, request, notice, meta_event
		String post_type = (String) maps.get("post_type");
		if (StrUtil.isBlank(post_type)) {
			throw new RuntimeException("消息类型不对");
		}
		switch (post_type) {
			case "meta_event": {
				// 这种类型的消息不用处理
				return;
			}
			case "message": {
				MessageVO messageVO = BeanUtil.copyProperties(maps,
						MessageVO.class, "");
				String message_type = messageVO.getMessage_type();
				if (StrUtil.isNotBlank(message_type) || StrUtil.equalsIgnoreCase(message_type, "private")) {
					// 好友消息或者群里私聊你的消息
					privateProcessor.runner(messageVO);
				} else {
					// 群消息
					System.err.println(messageVO);
				}
			}
			// TODO: 2023/5/30 后续还有很多别事件类型
		}
	}
}
