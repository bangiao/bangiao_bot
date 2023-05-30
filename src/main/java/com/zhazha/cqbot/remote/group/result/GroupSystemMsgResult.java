package com.zhazha.cqbot.remote.group.result;

import com.zhazha.cqbot.remote.BaseResult;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GroupSystemMsgResult extends BaseResult implements Serializable {
	private DataBean data;
	
	@Data
	public static class DataBean implements Serializable {
		private List<InvitedRequest> invited_requests; // 邀请消息列表
		private List<JoinRequest> join_requests; // 进群消息列表
	}
	
	@Data
	public static class InvitedRequest implements Serializable {
		private Long request_id;
		private Long invitor_uin;
		private String invitor_nick;
		private Long group_id;
		private String group_name;
		private boolean checked;
		private Long actor; // 处理者 未处理为 0
	}
	
	@Data
	public static class JoinRequest implements Serializable {
		private Long request_id;
		private Long invitor_uin;
		private String invitor_nick;
		private String message; // 验证消息
		private Long group_id;
		private String group_name;
		private boolean checked; // 是否已经被处理
		private Long actor; // 处理者 未处理为 0
	}
	
}
