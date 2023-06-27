package com.zhazha.cqhttp.remote.group.result;

import lombok.Data;

import javax.sql.rowset.BaseRowSet;
import java.io.Serializable;
import java.util.List;

@Data
public class GroupHonorInfoResult extends BaseRowSet implements Serializable {
	
	private DataBean data;
	
	@Data
	public static class DataBean implements Serializable {
		private TalkativeListBean current_talkative; // 当前龙王, 仅 type 为 talkative 或 all 时有数据
		private List<TalkativeListBean> emotion_list; // 快乐之源, 仅 type 为 emotion 或 all 时有数据
		private Long group_id;
		private List<TalkativeListBean> legend_list; // 群聊炽焰, 仅 type 为 legend 或 all 时有数据
		private List<TalkativeListBean> performer_lis; // 群聊之火, 仅 type 为 performer 或 all 时有数据
		private List<TalkativeListBean> strong_newbie_list; // 冒尖小春笋, 仅 type 为 strong_newbie 或 all 时有数据
		private List<TalkativeListBean> talkative_list; // 历史龙王, 仅 type 为 talkative 或 all 时有数据
		
		@Data
		public static class TalkativeListBean implements Serializable {
			private String avatar;
			private String description;
			private String nickname;
			private Long user_id;
			private Long day_count;
		}
	}
}
