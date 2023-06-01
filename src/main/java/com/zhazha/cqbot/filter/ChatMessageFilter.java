package com.zhazha.cqbot.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.bean.Config;
import com.zhazha.cqbot.chat.ChatEngine;
import com.zhazha.cqbot.constants.ConfigType;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.service.ConfigService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ChatMessageFilter implements MessageFilter {
    
    public static final String CMD_CHAT_ADD = Constants.CMD_CHAT + "add ";
    public static final String CMD_CHAT_GET = Constants.CMD_CHAT + "get";
    public static final String CMD_CHAT_DEL = Constants.CMD_CHAT + "delete ";
    
    @Resource
    private ConfigService configService;
    @Resource
    private ChatEngine chatEngine;
    
    @Override
    public Boolean match(BaseVO vo) {
        // 强调只能在私聊找执行该指令
        MessageVO messageVO = (MessageVO) vo;
        return StrUtil.startWithIgnoreCase(messageVO.getRaw_message(), Constants.CMD_CHAT);
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        MessageVO messageVO = (MessageVO) vo;
        String rawMessage = messageVO.getRaw_message();
        Long userId = messageVO.getUser_id();
        
        if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_ADD)) {
            // 只能为自己添加 api key
            return chatAdd(rawMessage, userId);
        } else if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_GET)) {
            // 只能看自己的
            return chatGet(userId);
        } else if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_DEL)) {
            // 只能删除自己的
            return chatDel(rawMessage);
        } else {
            String response = chatEngine.execute(messageVO);
            return ReplyVO.builder()
                    .at_sender(true)
                    .reply(response)
                    .build();
        }
    }
    
    private ReplyVO chatAdd(String rawMessage, Long userId) {
        String code = rawMessage.replace(CMD_CHAT_ADD, "").trim();
        Config config = configService.getById(Constants.adminQQ);
        if (null == config) {
            config = Config.builder()
                    .name(ConfigType.URL)
                    .status(0)
                    .type(ConfigType.CHATGPT.name())
                    .value1(ConfigType.URL)
                    .value2(code)
                    .value3(userId.toString())
                    .build();
        } else {
            config.setId(null);
            config.setValue3(userId.toString());
            config.setStatus(0);
            config.setValue2(code);
        }
        configService.saveOrUpdate(config);
        return ReplyVO.builder()
                .at_sender(true)
                .reply("保存成功").build();
    }
    
    private ReplyVO chatDel(String rawMessage) {
        String id = rawMessage.replace(CMD_CHAT_DEL, "").trim();
        configService.removeById(id);
        return ReplyVO.builder()
                .reply("删除成功")
                .at_sender(true)
                .build();
    }
    
    private ReplyVO chatGet(Long userId) {
        List<Config> configs = configService.listByQQ(userId);
        if (CollUtil.isEmpty(configs)) {
            return ReplyVO.builder()
                    .at_sender(true)
                    .reply("没有 user").build();
        }
        String s = configs.stream().map(config ->
                        new Triple(config.getId(), config.getValue1(), config.getValue2()))
                .collect(Collectors.toList())
                .toString();
        return ReplyVO.builder()
                .at_sender(true)
                .reply(s).build();
    }
    
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class Triple {
        private String id;
        private String name;
        private String key;
    }
}
