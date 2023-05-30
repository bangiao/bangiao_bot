package com.zhazha.cqbot.remote.group.result;

import com.zhazha.cqbot.remote.BaseResult;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GroupRootFilesResult extends BaseResult implements Serializable {
	private DataBean data;
	
	@Data
	public static class DataBean implements Serializable {
		private List<FilesBean> files;
		private List<FoldersBean> folders;
		
		@Data
		public static class FilesBean implements Serializable {
			private Long group_id;
			private String file_id;
			private String file_name;
			private Long busid; // 文件类型
			private Long file_size;
			private Long upload_time;
			private Long dead_time; // 过期时间
			private Long modify_time;
			private Long download_times;
			private Long uploader;
			private String uploader_name;
		}
		
		@Data
		public static class FoldersBean implements Serializable {
			private Long group_id;
			private String folder_id;
			private String folder_name;
			private Long create_time;
			private Long creator;
			private String creator_name;
			private Long total_file_count; // 子文件数量
		}
	}
}
