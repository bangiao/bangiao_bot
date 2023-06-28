package com.zhazha.cqhttp.vo;

import com.alibaba.cola.dto.DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseVO extends DTO {

//	time	int64	-	事件发生的unix时间戳
//	self_id	int64	-	收到事件的机器人的 QQ 号
//	post_type	string 参考	message, message_sent, request, notice, meta_event	表示该上报的类型, 消息, 消息发送, 请求, 通知, 或元事件
	
	private Long time;
	private Long self_id;
	private String post_type;
	
}
