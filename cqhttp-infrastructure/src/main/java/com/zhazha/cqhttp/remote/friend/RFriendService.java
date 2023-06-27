package com.zhazha.cqhttp.remote.friend;

import com.zhazha.cqhttp.remote.friend.fallback.RFriendServiceFallback;
import com.zhazha.cqhttp.remote.friend.result.FriendResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "friend", url = "${feign.customer.url}", fallback = RFriendServiceFallback.class)
public interface RFriendService {
	
	/**
	 * @return 获得机器人好友列表
	 */
	@GetMapping("/get_friend_list")
    FriendResult getFriendList();
	
	/**
	 * 好友祭天
	 *
	 * @param user_id 好友 id
	 */
	@GetMapping("/delete_friend")
	void deleteFriend(@RequestParam Long user_id);
	
	/**
	 * @param flag 加好友请求, 可以从事件源中获取该值
	 * @param approve 是否同意, 默认同意
	 * @param remark 添加好友时的备注
	 */
	@GetMapping("/set_friend_add_request")
	void addFriend(@RequestParam String flag, @RequestParam Boolean approve,
	               @RequestParam String remark);
	
}
