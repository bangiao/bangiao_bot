package com.zhazha.cqbot.controller.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 私聊消息
 */
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class MessageVO extends BaseVO implements Serializable {
//	* time	int64	-	事件发生的时间戳
//* self_id	int64	-	收到事件的机器人 QQ 号
//* post_type	string 参考	message	上报类型
//* message_type	string	private	消息类型
//* sub_type	string 参考	friend、group、group_self、other	消息子类型, 如果是好友则是 friend, 如果是群临时会话则是 group, 如果是在群中自身发送则是 group_self
//* message_id	int32	-	消息 ID
//* user_id	int64	-	发送者 QQ 号
//* message	message 参考	-	消息内容
//* raw_message	string 参考	-	原始消息内容
//* font	int32	-	字体
//* sender	object 参考	-	发送人信息
//	target_id	int64	-	接收者 QQ 号
//	temp_source	int	-	临时会话来源
	private String message_type;
	private String sub_type;
	private Long font;
	private Long group_id;
	private SenderBean sender;
	private Long user_id; // 发送者 QQ 号
	private Long message_id;
	private String message;
	private Long message_seq;
	private String raw_message;
	private AnonymousBean anonymous;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	public static class AnonymousBean {
		private Long id; // 匿名用户
		private String name; // 匿名用户名称
		private String flag; // 匿名用户 flag 用于禁言 API 是需要传入
	}
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Data
	public static class SenderBean implements Serializable {
		private Long age;
		private String area;
		private String card;
		private String level;
		private String nickname;
		private String role;
		private String sex;
		private String title;
		private Long user_id;
	}
}
