package com.zhazha.cqbot.chat;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.zhazha.cqhttp.bean.Config;
import com.zhazha.cqhttp.exception.NotifyException;
import com.zhazha.cqhttp.repository.ConfigRepository;
import com.zhazha.cqhttp.request.ChatRequest;
import com.zhazha.cqhttp.utils.CQCodeUtils;
import com.zhazha.cqhttp.vo.MessageVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChatEngine {
	
	@Resource
	private List<ChatRequest> chatRequestList;
	@Resource
	private SensitiveWordBs sensitiveWordBs;
	@Resource
	private ConfigRepository configRepository;
	
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
		Config config = configRepository.curNode();
		for (ChatRequest chatRequest : chatRequestList) {
			if (!chatRequest.match(config.getName())) {
				continue;
			}
			return chatRequest.request(s);
		}
		throw new NotifyException("没有符合的 chat gpt 节点");
	}
	
}
