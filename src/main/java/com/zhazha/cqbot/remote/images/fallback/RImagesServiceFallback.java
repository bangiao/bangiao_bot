package com.zhazha.cqbot.remote.images.fallback;

import com.zhazha.cqbot.remote.images.RImagesService;
import com.zhazha.cqbot.remote.images.result.ImageResult;
import com.zhazha.cqbot.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
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
