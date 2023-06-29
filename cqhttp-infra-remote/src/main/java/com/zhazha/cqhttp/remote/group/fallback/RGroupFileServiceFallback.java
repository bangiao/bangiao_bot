package com.zhazha.cqhttp.remote.group.fallback;

import com.zhazha.cqhttp.remote.group.RGroupFileService;
import com.zhazha.cqhttp.remote.group.result.GroupFileSystemInfoResult;
import com.zhazha.cqhttp.remote.group.result.GroupFileUrlResult;
import com.zhazha.cqhttp.remote.group.result.GroupFilesListResult;
import com.zhazha.cqhttp.remote.group.result.GroupRootFilesResult;
import com.zhazha.cqhttp.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RGroupFileServiceFallback implements RGroupFileService {
	
	
	
	@Override
	public void uploadGroupFile(Long group_id, String file, String name, String folder) {
		String s = "上传群文件失败";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void deleteGroupFile(Long group_id, String file_id, int busid) {
		String s = "deleteGroupFile";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void createGroupFileFolder(Long group_id, String name, String parent_id) {
		String s = "createGroupFileFolder";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void deleteGroupFolder(Long group_id, String folder_id) {
		String s = "deleteGroupFolder";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public GroupFileSystemInfoResult getGroupFileSystemInfo(Integer file_count, Integer limit_count, Long used_space, Long total_space) {
		String s = "getGroupFileSystemInfo";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public GroupRootFilesResult getGroupRootFiles(Long group_id) {
		String s = "getGroupRootFiles";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public GroupFilesListResult getGroupFilesByFolder(Long group_id, String folder_id) {
		String s = "getGroupFilesByFolder";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public GroupFileUrlResult getGroupFileUrl(Long group_id, String file_id, int busid) {
		String s = "getGroupFileUrl";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public void uploadPrivateFile(Long user_id, String file, String name) {
		String s = "uploadPrivateFile";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
}
