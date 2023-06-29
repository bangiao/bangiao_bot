package com.zhazha.cqhttp.remote.group.result;

import com.zhazha.cqhttp.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupInfoResult extends BaseResult implements Serializable {
	
	private DataBean data;
	
	@Data
	public static class DataBean implements Serializable {
		private Long group_create_time;
		private Long group_id;
		private Long group_level;
		private String group_name;
		private Long max_member_count; // 群容量
		private Long member_count; // 成员数
		private String group_memo; // 群备注
	}
}
