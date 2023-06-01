package com.zhazha.cqbot.chat;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.zhazha.cqbot.bean.Config;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.exception.NotifyException;
import com.zhazha.cqbot.request.ChatRequest;
import com.zhazha.cqbot.service.ConfigService;
import com.zhazha.cqbot.utils.CQCodeUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class ChatEngine {
	
	@Resource
	private List<ChatRequest> chatRequestList;
	@Resource
	private SensitiveWordBs sensitiveWordBs;
	@Resource
	private ConfigService configService;
	
	/**
	 * 返回 chat gpt 结果
	 *
	 * @param vo
	 * @return
	 */
	public String execute(MessageVO vo) {
		String message = vo.getRaw_message();
		String question = CQCodeUtils.getText(message);
		String s = sensitiveWordBs.replace(question, '_');
		Config config = configService.curNode();
		for (ChatRequest chatRequest : chatRequestList) {
			if (!chatRequest.match(config.getName())) {
				continue;
			}
			return chatRequest.request(vo.getUser_id(), s);
		}
		throw new NotifyException("没有符合的 chat gpt 节点");
	}
	
}
