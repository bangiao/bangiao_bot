package com.zhazha.cqbot.remote.friend.result;

import com.zhazha.cqbot.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public
class FriendResult extends BaseResult implements Serializable {
	private List<DataBean> data;
	
	@Data
	public static class DataBean implements Serializable {
		private String nickname;
		private String remark;
		private Long user_id;
	}
}