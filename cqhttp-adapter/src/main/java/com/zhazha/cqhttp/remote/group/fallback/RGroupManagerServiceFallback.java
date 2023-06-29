package com.zhazha.cqhttp.remote.group.fallback;

import com.zhazha.cqhttp.remote.group.RGroupManagerService;
import com.zhazha.cqhttp.remote.group.result.GroupNoticeResult;
import com.zhazha.cqhttp.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class RGroupManagerServiceFallback implements RGroupManagerService {
	
	
	
	@Override
	public void addGroup(String flag, String type, Boolean approve, String reason) {
		String s = "addGroup";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void setGroupManager(Long group_id, Long user_id, Boolean enable) {
		String s = "setGroupManager";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void setGroupCard(Long group_id, Long user_id, String card) {
		String s = "setGroupCard";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void setGroupSpecialTitle(Long group_id, Long user_id, String special_title, Integer duration) {
		String s = "setGroupSpecialTitle";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void setGroupBan(Long group_id, Long user_id, int duration) {
		String s = "setGroupBan";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void setGroupWholeBan(Long group_id, boolean enable) {
		String s = "setGroupWholeBan";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void setGroupAnonymousBan(Long group_id, String anonymous_flag, Integer duration) {
		String s = "setGroupAnonymousBan";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void setEssenceMsg(Integer message_id) {
		String s = "setEssenceMsg";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void deleteEssenceMsg(Integer message_id) {
		String s = "deleteEssenceMsg";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void sendGroupSign(Integer message_id) {
		String s = "sendGroupSign";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void sendGroupNotice(long group_id, String content, String image) {
		String s = "sendGroupNotice";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public GroupNoticeResult getGroupNotice(Long group_id) {
		String s = "getGroupNotice";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public void setGroupKick(Long group_id, Long user_id, boolean reject_add_request) {
		String s = "setGroupKick";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void setGroupLeave(Long group_id, boolean is_dismiss) {
		String s = "setGroupLeave";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
}
