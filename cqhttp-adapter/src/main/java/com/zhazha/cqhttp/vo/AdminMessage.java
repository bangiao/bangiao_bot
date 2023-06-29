package com.zhazha.cqhttp.vo;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.exception.NotifyException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminMessage extends UserMessage {
    
    public String getQq(String cmd) {
        String qq = this.getRaw_message().replaceFirst(cmd, "").trim();
        if (StrUtil.isBlank(qq)) {
            throw new NotifyException("没有操作目标");
        }
        return qq;
    }
    
    @Override
    public String getRaw_message() {
        return super.getRaw_message()
                .replace(CmdChatEnum.CMD_CHAT_ADD.getCmd(), "").trim();
    }
}
