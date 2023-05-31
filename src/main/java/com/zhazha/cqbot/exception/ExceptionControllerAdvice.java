package com.zhazha.cqbot.exception;

import com.zhazha.cqbot.remote.msg.RMessageService;
import com.zhazha.cqbot.utils.EmailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;


@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@Resource
	private RMessageService rMessageService;
	
	@ExceptionHandler(value = NotifyException.class)
	public String exception(NotifyException e) {
		if (log.isInfoEnabled()) {
			log.info("通知:", e);
		}
		if (e.getGroup_id() != null && e.getUser_id() != null) {
			rMessageService.sendMessage(e.getUser_id(), e.getGroup_id(), e.getMessage(), false);
		} else if (e.getGroup_id() != null && e.getUser_id() == null) {
			rMessageService.sendGroupMsg(e.getGroup_id(), e.getMessage(), false);
		}
		return "{\"reply\": \"通知: " + e.getMessage() + "[CQ:face,id=12]\"}";
	}
	
	@ExceptionHandler(value = BlockException.class)
	public String exception(BlockException e) {
		if (log.isInfoEnabled()) {
			log.error("黑名单:", e);
		}
		return "{\"reply\": \"黑名单: " + e.getMessage() + "[CQ:face,id=6]\"}";
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	public String exception(RuntimeException e) {
		if (log.isInfoEnabled()) {
			log.error("报错:", e);
		}
		log.error("我废了, 快通知我主人抢救我~~~");
		EmailUtils.exceptionSendEmail("糟糕, 出现异常了", e.getMessage());
		return "{\"reply\": \"我废了, 快通知我主人抢救我~~~[CQ:face,id=9]\"}";
	}
	
}
