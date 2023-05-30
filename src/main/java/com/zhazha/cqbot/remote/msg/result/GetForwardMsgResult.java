package com.zhazha.cqbot.remote.msg.result;

import com.zhazha.cqbot.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetForwardMsgResult extends BaseResult implements Serializable {
	
	private DataBean data;
	
	@Data
	public static class DataBean implements Serializable {
		private List<MessagesBean> messages;
		
		@Data
		public static class MessagesBean implements Serializable {
			
			private String content;
			private SenderBean sender;
			private Long time;
			
			@Data
			public static class SenderBean implements Serializable {
				private String nickname;
				private Long user_id;
			}
		}
	}
}
