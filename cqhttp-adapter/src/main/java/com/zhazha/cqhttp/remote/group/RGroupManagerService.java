package com.zhazha.cqhttp.remote.group;


import com.zhazha.cqhttp.remote.group.result.GroupNoticeResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "group-manager", url = "${feign.customer.url}")
//@FeignClient(name = "group-manager", url = "${feign.customer.url}", fallback = RGroupManagerServiceFallback.class)
public interface RGroupManagerService {
	/**
	 * 处理加群请求／邀请
	 *
	 * @param flag    加群请求的flag, 事件源中获取
	 * @param type    add 或 invite, 请求类型（需要和上报消息中的 sub_type 字段相符）
	 * @param approve 是否同意请求／邀请
	 * @param reason  拒绝理由（仅在拒绝时有效）
	 */
	@GetMapping("/set_group_add_request")
	void addGroup(@RequestParam String flag, @RequestParam String type,
	              @RequestParam Boolean approve, @RequestParam String reason);
	
	/**
	 * 设置群管理
	 *
	 * @param group_id 群号
	 * @param user_id  qq
	 * @param enable  true 为设置, false 为取消
	 */
	@GetMapping("/set_group_admin")
	void setGroupManager(@RequestParam Long group_id, @RequestParam Long user_id, @RequestParam Boolean enable);
	
	/**
	 * @param group_id 群
	 * @param user_id  qq
	 * @param card    群名片内容, 不填或空字符串表示删除群名片
	 */
	@GetMapping("/set_group_card")
	void setGroupCard(@RequestParam Long group_id, @RequestParam Long user_id, @RequestParam String card);
	
	/**
	 * 设置群专属头衔
	 *
	 * @param group_id       群
	 * @param user_id        qq
	 * @param special_title 专属头衔, 不填或空字符串表示删除专属头衔
	 * @param duration      专属头衔有效期, 单位秒, -1 表示永久, 不过此项似乎没有效果, 可能是只有某些特殊的时间长度有效, 有待测试
	 */
	@GetMapping("/set_group_special_title")
	void setGroupSpecialTitle(@RequestParam Long group_id, @RequestParam Long user_id,
	                          @RequestParam String special_title, @RequestParam Integer duration);
	
	/**
	 * 禁言群员
	 *
	 * @param group_id  群号
	 * @param user_id   要禁言的 QQ 号
	 * @param duration 30 * 60	禁言时长, 单位秒, 0 表示取消禁言
	 */
	@GetMapping("/set_group_ban")
	void setGroupBan(@RequestParam Long group_id, @RequestParam Long user_id, @RequestParam int duration);
	
	/**
	 * 群全员禁言
	 *
	 * @param group_id 群号
	 * @param enable   是否禁言
	 */
	@GetMapping("/set_group_whole_ban")
	void setGroupWholeBan(@RequestParam Long group_id, @RequestParam boolean enable);
	
	/**
	 * 群匿名用户禁言
	 *
	 * @param group_id      群号
	 * @param anonymous_flag 可选, 要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
	 * @param duration      30 * 60	禁言时长, 单位秒, 无法取消匿名用户禁言
	 */
	@GetMapping("/set_group_anonymous_ban")
	void setGroupAnonymousBan(@RequestParam Long group_id, @RequestParam String anonymous_flag, @RequestParam Integer duration);
	
	
	/**
	 * 设置精华消息
	 *
	 * @param message_id 消息ID
	 */
	@GetMapping("/set_essence_msg")
	void setEssenceMsg(@RequestParam Integer message_id);
	
	/**
	 * 移出精华消息
	 *
	 * @param message_id 消息ID
	 */
	@GetMapping("/delete_essence_msg")
	void deleteEssenceMsg(@RequestParam Integer message_id);
	
	/**
	 * 群打卡
	 *
	 * @param message_id 群号
	 */
	@GetMapping("/send_group_sign")
	void sendGroupSign(@RequestParam Integer message_id);
	
	/**
	 * 发送群公告
	 *
	 * @param group_id 群号
	 * @param content  公告内容
	 * @param image    图片路径（可选）
	 */
	@GetMapping("/_send_group_notice")
	void sendGroupNotice(@RequestParam long group_id, @RequestParam String content, @RequestParam String image);
	
	/**
	 * 获取群公告
	 *
	 * @param group_id 群
	 * @return 群公告列表
	 */
	@GetMapping("/_get_group_notice")
    GroupNoticeResult getGroupNotice(@RequestParam Long group_id);
	
	/**
	 * 群友祭天
	 *
	 * @param group_id           群号
	 * @param user_id            要踢的 QQ 号
	 * @param reject_add_request 拒绝此人的加群请求, 默认 false
	 */
	@GetMapping("/set_group_kick")
	void setGroupKick(@RequestParam Long group_id, @RequestParam Long user_id, @RequestParam boolean reject_add_request);
	
	/**
	 * 退出群
	 *
	 * @param group_id   群号
	 * @param is_dismiss 是否解散, 如果登录号是群主, 则仅在此项为 true 时能够解散, 默认 false
	 */
	@GetMapping("/set_group_leave")
	void setGroupLeave(@RequestParam Long group_id, @RequestParam boolean is_dismiss);
}
