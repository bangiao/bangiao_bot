package com.zhazha.cqbot.remote.friend.fallback;

import com.zhazha.cqbot.remote.friend.RFriendService;
import com.zhazha.cqbot.remote.friend.result.FriendResult;
import com.zhazha.cqbot.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RFriendServiceFallback implements RFriendService {
	
	
	
	@Override
	public FriendResult getFriendList() {
		String s = "getFriendList";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
		return null;
	}
	
	@Override
	public void deleteFriend(Long user_id) {
		String s = "deleteFriend";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
	
	@Override
	public void addFriend(String flag, Boolean approve, String remark) {
		String s = "addFriend";
		log.error(s);
		EmailUtils.exceptionSendEmail(s, s);
	}
}
