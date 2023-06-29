package com.zhazha.cqhttp.command.chat;

import cn.hutool.core.collection.CollUtil;
import com.zhazha.cqhttp.bean.Config;
import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.repository.ConfigRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetChatCommand implements ChatCommand {
    
    @Resource
    private ConfigRepository configRepository;
    
    @Override
    public CmdChatEnum getMode() {
        return CmdChatEnum.CMD_CHAT_GET;
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        Long userId = adminMessage.getUser_id();
        List<Config> configs = configRepository.listByQQ(userId);
        if (CollUtil.isEmpty(configs)) {
            return ReplyVO.builder()
                    .at_sender(true)
                    .reply("没有配置信息").build();
        }
        String s = configs.stream().map(config ->
                        new Triple(config.getId(), config.getValue1(), config.getValue2())
                )
                .toList()
                .toString();
        return ReplyVO.builder()
                .at_sender(true)
                .reply(s).build();
    }
    
}
