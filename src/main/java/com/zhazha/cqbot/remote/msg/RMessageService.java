package com.zhazha.cqbot.remote.msg;

import com.zhazha.cqbot.remote.msg.fallback.RMessageServiceFallback;
import com.zhazha.cqbot.remote.msg.result.GetForwardMsgResult;
import com.zhazha.cqbot.remote.msg.result.GetMsgResult;
import com.zhazha.cqbot.remote.msg.result.SendMsgResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "message", url = "${feign.customer.url}", fallback = RMessageServiceFallback.class)
public interface RMessageService {
	
	/**
	 * 发送消息
	 *
	 * @param user_id
	 * @param group_id
	 * @param message
	 * @param auto_escape 消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) , 只在 message 字段是字符串时有效
	 * @return
	 */
	@GetMapping(value = "/send_private_msg")
	SendMsgResult sendMessage(@RequestParam Long user_id, @RequestParam Long group_id, @RequestParam String message, @RequestParam Boolean auto_escape);
	
	/**
	 * 发送群消息
	 * @param group_id
	 * @param message
	 * @param auto_escape 消息内容是否作为纯文本发送 ( 即不解析 CQ 码 ) , 只在 message 字段是字符串时有效
	 * @return
	 */
	@GetMapping("/send_group_msg")
	SendMsgResult sendGroupMsg(@RequestParam Long group_id, @RequestParam String message, @RequestParam Boolean auto_escape);
	
	/**
	 * 根据消息id拿到相关数据
	 *
	 * @param message_id 消息id, 上面的发送消息的响应就有一个消息id
	 * @return 拿到消息的信息, 比如该消息的发送者, 消息的内容, 消息的id, 还有消息的类型等
	 */
	@GetMapping("/get_msg")
	GetMsgResult getMsg(@RequestParam long message_id);
	
	/**
	 * 撤回消息
	 *
	 * @param message_id 需要撤回的消息
	 */
	@GetMapping("/delete_msg")
	void deleteMsg(@RequestParam long message_id);
	
	/**
	 * 获取转发消息列表
	 *
	 * @param message_id 转发消息的id
	 * @return 得到转发消息列表
	 */
	@GetMapping("/get_forward_msg")
	GetForwardMsgResult getForwardMsg(@RequestParam String message_id);
	
}
