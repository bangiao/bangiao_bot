package com.zhazha.cqbot.request;

import cn.hutool.core.collection.CollUtil;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.zhazha.cqbot.bean.Config;
import com.zhazha.cqbot.exception.ChatException;
import com.zhazha.cqbot.service.ConfigService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 感谢 chatanywhere/GPT_API_free
 * <p>
 * <a href="https://github.com/chatanywhere/GPT_API_free">感谢 chatanywhere 欢迎大家去 star </a>
 */
@Component
public class ThanksChatRequest implements ChatRequest {
	
	@Resource
	private ConfigService configService;
	
	@Override
	public String request(Long qq, String question) throws ChatException {
		List<Config> configList = configService.listByQQ(qq);
		
		if (CollUtil.isEmpty(configList)) {
			throw new ChatException("chatgpt测试节点 apiKey 不存在, 请到 github 上的 chatanywhere/GPT_API_free 仓库中申请 " +
					"然后添加该属于你自己的 api 哦");
		}
		
		String host = configList.get(0).getValue1();
		
		List<String> apiKeyList = configList.stream().map(Config::getValue2).collect(Collectors.toList());
		
		//国内需要代理 国外不需要
//		Proxy proxy = Proxys.http("127.0.0.1", 1080);
		
		ChatGPT chatGPT = ChatGPT.builder()
				.timeout(1800)
				.apiKeyList(apiKeyList)
//				.proxy(proxy)
				.apiHost(host)
				.build()
				.init();
		
		Message system = Message.ofSystem("Let's think step by step");
		Message message = Message.of(question);
		
		ChatCompletion chatCompletion = ChatCompletion.builder()
				.model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
				.messages(Arrays.asList(message, system))
				.maxTokens(3000)
				.temperature(0.9)
				.build();
		ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
		
		return response.getChoices().stream()
				.map(chatChoice -> chatChoice.getMessage().getContent())
				.collect(Collectors.joining());
	}
}
