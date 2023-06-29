package com.zhazha.cqhttp.remote.images.fallback;

import com.zhazha.cqhttp.remote.images.RImagesService;
import com.zhazha.cqhttp.remote.images.result.ImageResult;
import com.zhazha.cqhttp.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;

//@Component
@Slf4j
public class RImagesServiceFallback implements RImagesService {
	
	
	
	@Override
	public ImageResult getImage(String file) {
		String s = "获取图片失败";
		log.error(s);
		EmailUtils.exceptionSendEmail("图片", s);
		return null;
	}
}
