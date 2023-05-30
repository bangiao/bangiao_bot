package com.zhazha.cqbot.remote.group.result;

import com.zhazha.cqbot.remote.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class GroupFileUrlResult extends BaseResult implements Serializable {
	
	private String url; // 文件下载链接
	
}
