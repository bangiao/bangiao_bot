package com.zhazha.cqhttp.remote.group;

import com.zhazha.cqhttp.remote.group.result.GroupFileSystemInfoResult;
import com.zhazha.cqhttp.remote.group.result.GroupFileUrlResult;
import com.zhazha.cqhttp.remote.group.result.GroupFilesListResult;
import com.zhazha.cqhttp.remote.group.result.GroupRootFilesResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "group-file", url = "${feign.customer.url}")
//@FeignClient(name = "group-file", url = "${feign.customer.url}", fallback = RGroupManagerServiceFallback.class)
public interface RGroupFileService {
	
	/**
	 * 上传群文件
	 * 只能上传本地文件, 需要上传 http 文件的话请先调用 download_file API下载
	 *
	 * @param group_id
	 * @param file    本地文件路径
	 * @param name    储存名称
	 * @param folder  父目录ID 在不提供 folder 参数的情况下默认上传到根目录
	 */
	@GetMapping("/upload_group_file")
	void uploadGroupFile(@RequestParam Long group_id, @RequestParam String file,
	                     @RequestParam String name, @RequestParam String folder);
	
	/**
	 * 删除群文件
	 *
	 * @param group_id
	 * @param file_id  文件ID 参考 File 对象
	 * @param busid   文件类型 参考 File 对象
	 */
	@GetMapping("/delete_group_file")
	void deleteGroupFile(@RequestParam Long group_id, @RequestParam String file_id, @RequestParam int busid);
	
	/**
	 * 创建群文件文件夹
	 * 仅能在根目录创建文件夹
	 *
	 * @param group_id
	 * @param name     文件夹名称
	 * @param parent_id 仅能为 /
	 */
	@GetMapping("/create_group_file_folder")
	void createGroupFileFolder(@RequestParam Long group_id, @RequestParam String name, @RequestParam String parent_id);
	
	/**
	 * 删除群文件文件夹
	 *
	 * @param group_id
	 * @param folder_id 文件夹ID 参考 Folder 对象
	 */
	@GetMapping("/delete_group_folder")
	void deleteGroupFolder(@RequestParam Long group_id, @RequestParam String folder_id);
	
	
	/**
	 * 获取群文件系统信息
	 *
	 * @param file_count  文件总数
	 * @param limit_count 文件上限
	 * @param used_space  已使用空间
	 * @param total_space 空间上限
	 * @return 群文件系统信息
	 */
	@GetMapping("/get_group_file_system_info")
	GroupFileSystemInfoResult getGroupFileSystemInfo(@RequestParam Integer file_count, @RequestParam Integer limit_count,
	                                                 @RequestParam Long used_space, @RequestParam Long total_space);
	
	
	/**
	 * 获取群根目录文件列表
	 *
	 * @param group_id
	 * @return 根目录文件列表
	 */
	@GetMapping("/get_group_root_files")
	GroupRootFilesResult getGroupRootFiles(@RequestParam Long group_id);
	
	
	/**
	 * 获取群子目录文件列表
	 *
	 * @param group_id
	 * @param folder_id 文件夹ID 参考 Folder 对象
	 * @return
	 */
	@GetMapping("/get_group_files_by_folder")
	GroupFilesListResult getGroupFilesByFolder(@RequestParam Long group_id, @RequestParam String folder_id);
	
	/**
	 * 获取群文件资源链接
	 *
	 * @param group_id
	 * @param file_id  文件ID 参考 File 对象
	 * @param busid   文件类型 参考 File 对象
	 * @return 文件 url
	 */
	@GetMapping("/get_group_files_by_folder")
	GroupFileUrlResult getGroupFileUrl(@RequestParam Long group_id, @RequestParam String file_id, @RequestParam int busid);
	
	
	/**
	 * 上传私聊文件
	 * 只能上传本地文件, 需要上传 http 文件的话请先调用 download_file API下载
	 *
	 * @param user_id
	 * @param file   本地文件路径
	 * @param name   文件名称
	 */
	//#
	@GetMapping("/get_group_files_by_folder")
	void uploadPrivateFile(@RequestParam Long user_id, @RequestParam String file, @RequestParam String name);
	
}
