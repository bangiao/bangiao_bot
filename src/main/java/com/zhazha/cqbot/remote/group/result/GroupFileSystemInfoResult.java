package com.zhazha.cqbot.remote.group.result;

import com.zhazha.cqbot.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupFileSystemInfoResult extends BaseResult implements Serializable {
	
	private DataBean data;
	
	@Data
	public static class DataBean implements Serializable {
		private Long file_count;
		private Long limit_count;
		private Long used_space;
		private Long total_space;
		private Long group_id;
	}
}
