package com.zhazha.cqbot;

//import cn.hutool.core.collection.CollUtil;
//import cn.hutool.core.util.StrUtil;
//import com.unfbx.chatgpt.OpenAiClient;
//import com.unfbx.chatgpt.entity.chat.ChatCompletion;
//import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
//import com.unfbx.chatgpt.entity.chat.Message;
//import com.unfbx.chatgpt.function.KeyRandomStrategy;
//import com.zhazha.cqbot.bean.Config;
//import com.zhazha.cqbot.exception.ChatException;

import okhttp3.*;
import org.junit.jupiter.api.Test;

public class ChatTest {
    
    @Test
    public void test02() throws Exception {
//        try {
//            Integer lock = lockMap.putIfAbsent(question.hashCode(), 1);
//            if (null != lock) {
//                return "一次只能回答个问题";
//            }
//
//            sendMessageUtils.sendMessage(qq, "您的问题是: \"" + question + "\"请稍等...", false);
//
//            List<Config> configList = configService.listByName(NODE_NAME);
//
//            if (CollUtil.isEmpty(configList)) {
//                throw new ChatException("chatgpt测试节点 apiKey 不存在, 请到 github 上的 chatanywhere/GPT_API_free 仓库中申请 " +
//                        "然后添加该属于你自己的 api 哦");
//            }
//
//            Config config = configList.get(0);
//            String host = config.getValue1();
//            if (StrUtil.isBlank(host) || !StrUtil.containsIgnoreCase(NODE_NAME, config.getName())) {
//                throw new ChatException("openAI访问的网址为空或者不准确");
//            }
//
//            List<String> apiKeyList = configList.stream().map(Config::getValue2).collect(Collectors.toList());
//
//            OpenAiClient openAiClient = OpenAiClient.builder()
//                    .apiKey(apiKeyList)
//                    //自定义key的获取策略：默认KeyRandomStrategy
//                    .keyStrategy(new KeyRandomStrategy())
//                    //自己做了代理就传代理地址，没有可不不传
//                    .apiHost(host)
//                    .okHttpClient(okHttpClient)
//                    .build();
//
//            //聊天模型：gpt-3.5
//            Message message = Message.builder().role(Message.Role.USER)
//                    .content("Let's think step by step.")
//                    .name("bangiao")
//                    .content( question).build();
//            ChatCompletion chatCompletion = ChatCompletion.builder()
//                    .messages(List.of(message)).build();
//            ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);
//
//            return chatCompletionResponse.getChoices().stream()
//                    .map(chatChoice -> chatChoice.getMessage().getContent())
//                    .collect(Collectors.joining());
//        } finally {
//            lockMap.remove(question.hashCode());
//        }
    }
    
    @Test
    public void test01() throws Exception {
        String url = "https://api.aichatos.cloud/api/generateStream";
        String requestBody = "{\"prompt\":\"g1什么情况下会触发混合gc?\",\"userId\":\"#/chat/1685468457155\",\"network\":true,\"system\":\"\",\"withoutContext\":false,\"stream\":false}";
        
        OkHttpClient client = new OkHttpClient();
        
        Request request = new Request.Builder()
                .url(url)
                .header("Host", "api.aichatos.cloud")
                .header("Connection", "keep-alive")
                .header("sec-ch-ua", "\"Microsoft Edge\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24\"")
                .header("Accept", "application/json, text/plain, */*")
                .header("Content-Type", "application/json")
                .header("DNT", "1")
                .header("sec-ch-ua-mobile", "?0")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36 Edg/113.0.1774.57")
                .header("sec-ch-ua-platform", "\"Windows\"")
                .header("Origin", "https://chat12.yqcloud.top")
                .header("Sec-Fetch-Site", "cross-site")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Dest", "empty")
                .header("Referer", "https://chat12.yqcloud.top/")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .post(RequestBody.create(MediaType.parse("application/json"), requestBody))
                .build();
        
        Call call = client.newCall(request);
        Response response = call.execute();
        
        System.out.println("Response status code: " + response.code());
        assert response.body() != null;
        System.out.println("Response body: " + response.body().string());
        
    }
    
}
