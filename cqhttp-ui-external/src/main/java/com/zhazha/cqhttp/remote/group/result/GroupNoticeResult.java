package com.zhazha.cqhttp.remote.group.result;

import com.zhazha.cqhttp.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupNoticeResult extends BaseResult implements Serializable {
	
	private List<DataBean> data;
	
	@Data
	public static class DataBean implements Serializable {
		private String notice_id;
		private Long sender_id;
		private Long publish_time;
		private MessageBean message;
		
		@Data
		public static class MessageBean implements Serializable {
			private String text;
			private List<ImagesBean> images;
			
			@Data
			public static class ImagesBean implements Serializable {
				private String height;
				private String width;
				private String id;
			}
		}
	}
}
