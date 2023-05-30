package com.zhazha.cqbot.remote.group.fallback;

import com.zhazha.cqbot.remote.group.RGroupInfoService;
import com.zhazha.cqbot.remote.group.result.*;
import com.zhazha.cqbot.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RGroupInfoServiceFallback implements RGroupInfoService {
	
	
	
	@Override
	public GroupInfoResult getGroupInfo(Long group_id, Boolean no_cache) {
		String s = "getGroupInfo";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public GroupInfoListResult groupList(Boolean no_cache) {
		String s = "groupList";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public GroupMemberResult getGroupMemberInfo(long group_id, long user_id, Boolean no_cache) {
		String s = "getGroupMemberInfo";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public GroupMemberListResult getGroupMemberList(long group_id, Boolean no_cache) {
		String s = "getGroupMemberList";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public GroupHonorInfoResult getGroupHonorInfo(Long group_id, String type) {
		String s = "getGroupHonorInfo";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public GroupSystemMsgResult getGroupSystemMsg() {
		String s = "getGroupSystemMsg";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public void setGroupName(Long group_id, String group_name) {
		String s = "setGroupName";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
}
