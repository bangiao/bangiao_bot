package com.zhazha.cqbot.exception;

import cn.hutool.json.JSONUtil;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.remote.msg.RMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;


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
		Random random = new Random(new Date().getTime());
		int i = random.nextInt(125);
		ReplyVO replyVO = ReplyVO.builder()
				.reply("通知: " + e.getMessage() + " [CQ:face,id=" + i + "] ")
				.build();
		return JSONUtil.toJsonStr(replyVO);
	}
	
	@ExceptionHandler(value = BlockException.class)
	public String exception(BlockException e) {
		if (log.isInfoEnabled()) {
			log.error("黑名单:", e);
		}
		ReplyVO replyVO = ReplyVO.builder()
				.reply("警告: " + e.getMessage() + " [CQ:face,id=6] ")
				.at_sender(true)
				.build();
		return JSONUtil.toJsonStr(replyVO);
	}
	
//	@ExceptionHandler(value = RuntimeException.class)
//	public String exception(RuntimeException e) {
//		if (log.isInfoEnabled()) {
//			log.error("报错:", e);
//		}
//		log.error("我废了, 快通知我主人抢救我~~~");
//		EmailUtils.exceptionSendEmail("糟糕, 出现异常了", e.getMessage());
//		ReplyVO replyVO = ReplyVO.builder()
//				.reply("我废了, 快通知我主人抢救我~~~[CQ:face,id=9] ")
//				.build();
//		return JSONUtil.toJsonStr(replyVO);
//	}
	
}
