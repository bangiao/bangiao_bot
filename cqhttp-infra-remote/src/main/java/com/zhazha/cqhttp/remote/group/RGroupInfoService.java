package com.zhazha.cqhttp.remote.group;

import com.zhazha.cqhttp.remote.group.fallback.RGroupInfoServiceFallback;
import com.zhazha.cqhttp.remote.group.result.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "group", url = "${feign.customer.url}", fallback = RGroupInfoServiceFallback.class)
public interface RGroupInfoService {
	
	/**
	 * @param group_id 群id
	 * @param no_cache 是否不使用缓存（使用缓存可能更新不及时, 但响应更快） 默认为 false
	 * @return 群信息
	 */
	@GetMapping("/get_group_info")
	GroupInfoResult getGroupInfo(@RequestParam Long group_id, @RequestParam Boolean no_cache);
	
	/**
	 * @param no_cache 是否不使用缓存（使用缓存可能更新不及时, 但响应更快） 默认为 false
	 * @return 获取群列表
	 */
	@GetMapping("/get_group_list")
	GroupInfoListResult groupList(@RequestParam Boolean no_cache);
	
	/**
	 * @param group_id 群号
	 * @param user_id  群员qq
	 * @param no_cache 是否不使用缓存（使用缓存可能更新不及时, 但响应更快） 默认为 false
	 * @return 获得群成员信息
	 */
	@GetMapping("/get_group_member_info")
	GroupMemberResult getGroupMemberInfo(@RequestParam long group_id,
	                                     @RequestParam long user_id,
	                                     @RequestParam Boolean no_cache);
	
	/**
	 * @param group_id 群qq
	 * @param no_cache 是否不使用缓存（使用缓存可能更新不及时, 但响应更快） 默认为 false
	 * @return 获得群成员列表
	 */
	@GetMapping("/get_group_member_list")
	GroupMemberListResult getGroupMemberList(@RequestParam long group_id,
	                                         @RequestParam Boolean no_cache);
	
	
	/**
	 * @param group_id 群
	 * @param type     要获取的群荣誉类型,
	 *                 可传入 talkative performer legend strong_newbie emotion 以分别获取单个类型的群荣誉数据,
	 *                 或传入 all 获取所有数据
	 * @return 龙王等信息
	 */
	@GetMapping("/get_group_honor_info")
	GroupHonorInfoResult getGroupHonorInfo(@RequestParam Long group_id,
	                                       @RequestParam String type);
	
	/**
	 * @return 获取群系统消息
	 * invited_requests  邀请消息列
	 * join_requests    进群消息列表
	 */
	@GetMapping("/get_group_system_msg")
	GroupSystemMsgResult getGroupSystemMsg();
	
	/**
	 * 设置群名
	 *
	 * @param group_id   群号
	 * @param group_name 群名
	 */
	@GetMapping("/set_group_name")
	void setGroupName(@RequestParam Long group_id, @RequestParam String group_name);
	
}
