package com.zhazha.cqhttp.controller;

import cn.hutool.json.JSONUtil;
import com.zhazha.cqhttp.exception.BlockException;
import com.zhazha.cqhttp.exception.ChatException;
import com.zhazha.cqhttp.exception.NotifyException;
import com.zhazha.cqhttp.utils.EmailUtils;
import com.zhazha.cqhttp.vo.ReplyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.Random;


@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(value = NotifyException.class)
	public String exception(NotifyException e) {
		if (log.isInfoEnabled()) {
			log.info("通知:", e);
		}
		Random random = new Random(new Date().getTime());
		int i = random.nextInt(125);
		ReplyVO replyVO = ReplyVO.builder()
				.reply("通知: " + e.getMessage() + " [CQ:face,id=" + i + "] ")
				.at_sender(true)
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
	
	@ExceptionHandler(value = ChatException.class)
	public String exception(ChatException e) {
		if (log.isInfoEnabled()) {
			log.error("chatgpt:", e);
		}
		ReplyVO replyVO = ReplyVO.builder()
				.reply("chatgpt警告: " + e.getMessage() + " [CQ:face,id=6] ")
				.at_sender(true)
				.delete(true)
				.build();
		return JSONUtil.toJsonStr(replyVO);
	}
	
	@ExceptionHandler(value = RuntimeException.class)
	public String exception(RuntimeException e) {
		if (log.isInfoEnabled()) {
			log.error("报错:", e);
		}
		log.error("我废了, 快通知我主人抢救我~~~");
		EmailUtils.exceptionSendEmail("糟糕, 出现异常了", e.getMessage());
		ReplyVO replyVO = ReplyVO.builder()
				.reply("我摔倒了, 快通知我主人来扶我~~~[CQ:face,id=9] ")
				.at_sender(true)
				.build();
		return JSONUtil.toJsonStr(replyVO);
	}
	
}
