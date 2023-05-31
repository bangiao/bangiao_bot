package com.zhazha.cqbot.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyVO {
	private String reply;
	private Boolean auto_escape = false;
	private Boolean at_sender;
	private Boolean delete;
	private Boolean kick;
	private Boolean ban;
	private Integer ban_duration;
}
