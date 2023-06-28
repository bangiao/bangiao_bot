package com.zhazha.cqhttp.vo;

import com.alibaba.cola.dto.DTO;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReplyVO extends DTO {
	private String reply;
	private Boolean auto_escape;
	private Boolean at_sender;
	private Boolean delete;
	private Boolean kick;
	private Boolean ban;
	private Integer ban_duration;
}
