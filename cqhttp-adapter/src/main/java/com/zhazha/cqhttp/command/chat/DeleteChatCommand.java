package com.zhazha.cqhttp.command.chat;

import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.repository.ConfigRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class DeleteChatCommand implements ChatCommand {
    
    @Resource
    private ConfigRepository configRepository;
    
    @Override
    public CmdChatEnum getMode() {
        return CmdChatEnum.CMD_CHAT_DEL;
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        configRepository.removeByQQ(adminMessage.getUser_id().toString());
        return ReplyVO.builder()
                .reply("删除成功")
                .at_sender(true)
                .build();
    }
}
