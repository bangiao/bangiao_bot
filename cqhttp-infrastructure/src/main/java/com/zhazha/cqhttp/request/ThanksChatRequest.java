package com.zhazha.cqhttp.request;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.plexpt.chatgpt.ChatGPT;
import com.plexpt.chatgpt.entity.chat.ChatCompletion;
import com.plexpt.chatgpt.entity.chat.ChatCompletionResponse;
import com.plexpt.chatgpt.entity.chat.Message;
import com.zhazha.cqhttp.bean.Config;
import com.zhazha.cqhttp.exception.ChatException;
import com.zhazha.cqhttp.repository.ConfigRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 感谢 chatanywhere/GPT_API_free
 * <p>
 * <a href="https://github.com/chatanywhere/GPT_API_free">感谢 chatanywhere 欢迎大家去贡献 star </a>
 */
@Component
public class ThanksChatRequest implements ChatRequest {
    
    @Resource
    private ConfigRepository configRepository;
    public static final String NODE_NAME = "chatanywhere";
    private final Map<Integer, Integer> lockMap = new ConcurrentHashMap<>();
    
    @Override
    public Boolean match(String key) {
        return StrUtil.equalsIgnoreCase(NODE_NAME, key);
    }
    
    @Override
    public String request(String question) throws ChatException {
        try {
            Integer lock = lockMap.putIfAbsent(question.hashCode(), 1);
            if (null != lock) {
//                throw new ChatException("一次只能回答个问题");
                return "一次只能回答个问题";
            }
            List<Config> configList = configRepository.listByName(NODE_NAME);
            
            if (CollUtil.isEmpty(configList)) {
                throw new ChatException("chatgpt测试节点 apiKey 不存在, 请到 github 上的 chatanywhere/GPT_API_free 仓库中申请 " +
                        "然后添加该属于你自己的 api 哦");
            }
            
            Config config = configList.get(0);
            String host = config.getValue1();
            if (StrUtil.isBlank(host) || !StrUtil.containsIgnoreCase(NODE_NAME, config.getName())) {
                throw new ChatException("openAI访问的网址为空或者不准确");
            }
            
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
            Message message = Message.of(question + " 用中文回答");

            ChatCompletion chatCompletion = ChatCompletion.builder()
                    .model("gpt-3.5-turbo-0613")
                    .messages(Arrays.asList(message, system))
                    .maxTokens(3000)
                    .temperature(0.9)
                    .build();
            ChatCompletionResponse response = chatGPT.chatCompletion(chatCompletion);

            return response.getChoices().stream()
                    .map(chatChoice -> chatChoice.getMessage().getContent())
                    .collect(Collectors.joining());
        } finally {
            lockMap.remove(question.hashCode());
        }
    }
}
