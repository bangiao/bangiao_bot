package com.zhazha.cqbot.remote.msg.result;

import com.zhazha.cqbot.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SendMsgResult extends BaseResult implements Serializable {
	
	private MsgData data;
	
	@Data
	public static class MsgData implements Serializable {
		private Long message_id;
	}
}
