package com.zhazha.cqhttp.command.chat;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.bean.Config;
import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.repository.ConfigRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListChatCommand implements ChatCommand {
    
    @Resource
    private ConfigRepository configRepository;
    
    @Override
    public CmdChatEnum getMode() {
        return CmdChatEnum.CMD_CHAT_LIST;
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        String userId = adminMessage.getUser_id().toString();
        String rawMessage = adminMessage.getRaw_message();
        if (StrUtil.isBlank(userId) || StrUtil.isBlank(rawMessage)) {
            return ReplyVO.builder()
                    .at_sender(true)
                    .reply("你的格式不对")
                    .build();
        }
        if (!StrUtil.equalsIgnoreCase(userId, Constants.ADMIN_QQ)) {
            return ReplyVO.builder()
                    .at_sender(true)
                    .reply("你的权限不够")
                    .build();
        }
        List<Config> configList = configRepository.listChat(userId);
        String s = configList.stream().map(config ->
                        new Triple(config.getId(), config.getValue1(), config.getValue2())
                )
                .toList()
                .toString();
        return ReplyVO.builder()
                .at_sender(true)
                .reply(s)
                .build();
    }
}
