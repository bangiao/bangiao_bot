package com.zhazha.cqbot.functioncall;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.zhazha.cqbot.functioncall.bean.ApiRequest;
import com.zhazha.cqbot.functioncall.bean.FunctionCall;
import com.zhazha.cqbot.functioncall.bean.Message;
import com.zhazha.cqbot.functioncall.bean.Parameter;
import com.zhazha.cqbot.request.ThanksChatRequest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;

@SpringBootTest
public class TestFunctionCall {
    
    @Resource
    private ThanksChatRequest thanksChatRequest;
    
    @Test
    public void test01() throws Exception {
        String name = "Alice";
        int age = 30;
        String message = StrUtil.format("name = {}, age = {}", name, age);
        System.out.println(message);
        String json = """
                {
                    "model": "gpt-3.5-turbo-0613",
                    "messages": [
                        {
                            "role": "user",
                            "content": "Send Cobus from humanfirst ai an email asking for the monthly report?"
                        }
                    ]
                }
                """;
        ApiRequest apiRequest = JSONUtil.toBean(json, ApiRequest.class);
        ArrayList<FunctionCall> functions = new ArrayList<>();
        FunctionCall functionCall = new FunctionCall();
        functionCall.setName("sendSimpleEmail");
        functionCall.setDescription("发送邮箱的函数");
        ArrayList<Parameter> parameters = new ArrayList<>();
        Parameter parameter = new Parameter();
        parameter.setName("subject");
        parameter.setType("String");
        parameter.setDescription("邮箱标题");
        parameters.add(parameter);
        parameter = new Parameter();
        parameter.setName("body");
        parameter.setType("String");
        parameter.setDescription("邮箱内容");
        parameters.add(parameter);
        parameter = new Parameter();
        parameter.setName("toEmail");
        parameter.setType("String");
        parameter.setDescription("邮件发送给谁");
        parameters.add(parameter);
        functionCall.setParameters(parameters);
        functions.add(functionCall);
        apiRequest.setFunctions(functions);
        String request = thanksChatRequest.request(JSONUtil.toJsonStr(apiRequest));
        System.err.println(request);
    }
    
    @Test
    public void test() throws Exception {
        String json = "{" +
                "\"model\": \"gpt-3.5-turbo-0613\"," +
                "\"messages\": [{" +
                "\"role\": \"user\"," +
                "\"content\": \"Send Cobus from humanfirst ai an email asking for the monthly report?\"" +
                "}]," +
                "\"functions\": [{" +
                "\"name\": \"send_email\"," +
                "\"description\": \"Please send an email.\"," +
                "\"parameters\": {" +
                "\"type\": \"object\"," +
                "\"properties\": {" +
                "\"to_address\": {" +
                "\"type\": \"string\"," +
                "\"description\": \"To address for email\"" +
                "}," +
                "\"subject\": {" +
                "\"type\": \"string\"," +
                "\"description\": \"subject of the email\"" +
                "}," +
                "\"body\": {" +
                "\"type\": \"string\"," +
                "\"description\": \"Body of the email\"" +
                "}" +
                "}" +
                "}" +
                "}]" +
                "}";
        
        ApiRequest request = JSONUtil.toBean(json, ApiRequest.class, false);
        
        System.out.println("Model: " + request.getModel());
        for (Message message : request.getMessages()) {
            System.out.println("Message:");
            System.out.println("- Role: " + message.getRole());
            System.out.println("- Content: " + message.getContent());
        }
        for (FunctionCall function : request.getFunctions()) {
            System.out.println("Function:");
            System.out.println("- Name: " + function.getName());
            System.out.println("- Description: " + function.getDescription());
            for (Parameter parameter : function.getParameters()) {
                System.out.println("- Parameter:");
                System.out.println("    - Name: " + parameter.getName());
                System.out.println("    - Type: " + parameter.getType());
                System.out.println("    - Description: " + parameter.getDescription());
            }
        }
    }
    
}
