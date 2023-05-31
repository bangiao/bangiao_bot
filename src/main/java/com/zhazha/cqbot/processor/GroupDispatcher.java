package com.zhazha.cqbot.processor;

import com.zhazha.cqbot.controller.vo.MessageVO;
import org.springframework.stereotype.Component;

@Component
public class GroupDispatcher {
	
	public void runner(MessageVO messageVO) {
		// TODO: 2023/5/31
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
	}
}
