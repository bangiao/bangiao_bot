package com.zhazha.cqbot.remote.msg.fallback;

import com.zhazha.cqbot.remote.msg.RMessageService;
import com.zhazha.cqbot.remote.msg.result.GetForwardMsgResult;
import com.zhazha.cqbot.remote.msg.result.GetMsgResult;
import com.zhazha.cqbot.remote.msg.result.SendMsgResult;
import com.zhazha.cqbot.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Component
public class RMessageServiceFallback implements RMessageService {
	
	@Override
	public SendMsgResult sendMessage(@RequestParam Long user_id, @RequestParam Long group_id, @RequestParam String message, @RequestParam Boolean auto_escape) {
		String content = "消息发送失败: " + message;
		log.error(content);
		EmailUtils.exceptionSendEmail("消息", content);
		return null;
	}
	
	@Override
	public SendMsgResult sendGroupMsg(@RequestParam Long group_id, @RequestParam String message, @RequestParam Boolean auto_escape) {
		String content = "消息发送失败: " + message;
		log.error(content);
		EmailUtils.exceptionSendEmail("消息", content);
		return null;
	}
	
	
	@Override
	public GetMsgResult getMsg(long message_id) {
		String content = "消息信息获取失败: " + message_id;
		log.error(content);
		EmailUtils.exceptionSendEmail("消息", content);
		return null;
	}
	
	@Override
	public void deleteMsg(long message_id) {
		String s = "撤回消息失败";
		log.error(s);
		EmailUtils.exceptionSendEmail("消息", s);
	}
	
	@Override
	public GetForwardMsgResult getForwardMsg(String message_id) {
		String msg = "消息转发失败";
		log.error(msg);
		EmailUtils.exceptionSendEmail("消息", msg);
		return null;
	}
}
