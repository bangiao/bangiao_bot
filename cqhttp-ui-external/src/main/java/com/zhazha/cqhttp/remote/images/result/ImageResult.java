package com.zhazha.cqhttp.remote.images.result;

import com.zhazha.cqhttp.remote.BaseResult;
import lombok.Data;

import java.io.Serializable;

public class ImageResult extends BaseResult {
	
	@Data
	public static class DataBean implements Serializable {
		private Long size;
		private String filename;
		private String url;
	}
	
}
