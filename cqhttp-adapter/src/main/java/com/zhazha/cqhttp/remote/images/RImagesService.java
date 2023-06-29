package com.zhazha.cqhttp.remote.images;


import com.zhazha.cqhttp.remote.images.result.ImageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "images", url = "${feign.customer.url}")
//@FeignClient(name = "images", url = "${feign.customer.url}", fallback = RImagesServiceFallback.class)
public interface RImagesService {
	
	/**
	 * @param file 图片缓存文件名
	 * @return 图片下载地址, 图片文件原名, 图片源文件大小
	 */
	@GetMapping("/get_image")
    ImageResult getImage(@RequestParam String file);

}
