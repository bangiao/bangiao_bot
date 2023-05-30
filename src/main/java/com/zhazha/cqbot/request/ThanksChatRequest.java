package com.zhazha.cqbot.request;

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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 感谢 chatanywhere/GPT_API_free
 * <p>
 * https://github.com/chatanywhere/GPT_API_free
 */
@Component
public class ThanksChatRequest implements ChatRequest {
	
	@Resource
	private ConfigService configService;
	
	@Override
	public String request(Long qq, String question) {
		String url = "https://api.chatanywhere.com.cn";
		Optional<Config> opt = configService.lambdaQuery()
				.eq(Config::getName, url)
				.eq(Config::getValue3, qq.toString())
				.oneOpt();
		
		if (opt.isEmpty()) {
			throw new ChatException("chatgpt测试节点 apiKey 不存在, 请到 github 上的 chatanywhere/GPT_API_free 仓库中申请");
		}
		
		Config config = opt.get();
		
		//国内需要代理 国外不需要
//		Proxy proxy = Proxys.http("127.0.0.1", 1080);
		
		ChatGPT chatGPT = ChatGPT.builder()
				.timeout(1800)
				.apiKey(config.getValue2())
//				.proxy(proxy)
				.apiHost(config.getValue1())
				.build()
				.init();
		
		
//		Message system = Message.ofSystem("Let's think step by step");
		Message message = Message.of(question);
		
		ChatCompletion chatCompletion = ChatCompletion.builder()
				.model(ChatCompletion.Model.GPT_3_5_TURBO.getName())
//				.messages(Arrays.asList(system, message))
				.messages(Arrays.asList(message))
				.maxTokens(3000)
				.temperature(0.9)
				.build();
		ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);
		return response.getChoices().stream()
				.map(chatChoice -> chatChoice.getMessage().getContent())
				.collect(Collectors.joining());
	}
}
