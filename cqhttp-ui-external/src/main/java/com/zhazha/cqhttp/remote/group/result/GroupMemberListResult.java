package com.zhazha.cqhttp.remote.group.result;

import com.zhazha.cqhttp.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMemberListResult extends BaseResult implements Serializable {
	
	private List<DataBean> data;
	
	@Data
	public static class DataBean implements Serializable {
		private Long age;
		private String area;
		private String card;
		private boolean card_changeable;
		private Long group_id;
		private Long join_time;
		private Long last_sent_time;
		private String level;
		private String nickname;
		private String role;
		private String sex;
		private Long shut_up_timestamp;
		private String title;
		private Long title_expire_time;
		private boolean unfriendly;
		private Long user_id;
	}
}
