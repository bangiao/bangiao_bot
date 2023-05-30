package com.zhazha.cqbot;

import okhttp3.*;
import org.junit.jupiter.api.Test;

public class ChatTest {
	
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
