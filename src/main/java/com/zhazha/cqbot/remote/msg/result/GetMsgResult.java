package com.zhazha.cqbot.remote.msg.result;

import com.zhazha.cqbot.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GetMsgResult extends BaseResult implements Serializable {
	
	private DataObj data;
	@Data
	public static class DataObj implements Serializable {
		private boolean group;
		private String message;
		private Long message_id;
		private String message_id_v2;
		private Long message_seq;
		private String message_type;
		private Long real_id;
		private SenderObj sender;
		private Long time;
		
		@Data
		public static class SenderObj implements Serializable {
			private String nickname;
			private Long user_id;
		}
	}
}
