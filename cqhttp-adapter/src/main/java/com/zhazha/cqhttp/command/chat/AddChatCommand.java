package com.zhazha.cqhttp.command.chat;

import com.zhazha.cqhttp.bean.Config;
import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.repository.ConfigRepository;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class AddChatCommand implements ChatCommand {
    @Resource
    private ConfigRepository configRepository;
    
    @Override
    public CmdChatEnum getMode() {
        return CmdChatEnum.CMD_CHAT_ADD;
    }
    
    @Override
    public ReplyVO execute(AdminMessage adminMessage) {
        String code = adminMessage.getRaw_message();
        Config config = configRepository.getById(Constants.ADMIN_QQ);
        String userId = adminMessage.getUser_id().toString();
        if (null == config) {
            config = Config.createConfig(userId, code);
        } else {
            config.setValue3(userId);
            config.setStatus(0);
            config.setValue2(code);
        }
        configRepository.saveOrUpdate(config);
        return ReplyVO.builder()
                .at_sender(true)
                .reply("保存成功").build();
    }
    
}
