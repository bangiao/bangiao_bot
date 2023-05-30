package com.zhazha.cqbot.utils;

import org.junit.jupiter.api.Test;

class CQCodeUtilsTest {
	
	
	@Test
	public void test01() throws Exception {
		String message = "[CQ:face,id=27]我[CQ:at,qq=2133445917] [CQ:face,id=4]丢 \\r\\n这[CQ:face,id=7]是\\r\\n什[CQ:at,qq=1250377248] 么[CQ:face,id=14]鬼\\r\\n?[CQ:image,file=451c4c6b4a6cdaa1c5c9a163b4ea7db2.image,subType=1,url=https://gchat.qpic.cn/gchatpic_new/2033445917/1058735112-2435831904-451C4C6B4A6CDAA1C5C9A163B4EA7DB2/0?term=2&amp;is_origin=0]";
		String pattern = "(\\[[^\\]]*\\])|(\\r\\n)|(\\n)";
		String result = message.replaceAll(pattern, "");
		System.out.println(result);
	}
	@Test
	void getText() {
		String text = "[CQ:face,id=27]我[CQ:at,qq=2133445917] [CQ:face,id=4]丢 \\r\\n这[CQ:face,id=7]是\\r\\n什[CQ:at,qq=1250377248] 么[CQ:face,id=14]鬼\\r\\n?[CQ:image,file=451c4c6b4a6cdaa1c5c9a163b4ea7db2.image,subType=1,url=https://gchat.qpic.cn/gchatpic_new/2033445917/1058735112-2435831904-451C4C6B4A6CDAA1C5C9A163B4EA7DB2/0?term=2&amp;is_origin=0]";
//		String text = "hello world";
		String s = CQCodeUtils.getText(text);
		System.err.println(s);
	}
}