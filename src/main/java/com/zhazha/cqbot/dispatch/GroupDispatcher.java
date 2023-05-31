package com.zhazha.cqbot.dispatch;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.zhazha.cqbot.controller.vo.MessageVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class GroupDispatcher {
	
	@Resource
	private SensitiveWordBs sensitiveWordBs;
	
	public String dispatch(MessageVO messageVO) {
		// TODO: 2023/5/31
		MessageVO.SenderBean sender = messageVO.getSender();
		String raw_message = messageVO.getRaw_message();
		Long group_id = messageVO.getGroup_id();
		
		// 群消息
		// normal、anonymous、notice
		switch (messageVO.getSub_type()) {
			case "normal": {
				System.err.println("1");
			}
			case "anonymous": {
				// 执行严格的文字审批
				System.err.println("2");
			}
		}
		return null;
	}
}
