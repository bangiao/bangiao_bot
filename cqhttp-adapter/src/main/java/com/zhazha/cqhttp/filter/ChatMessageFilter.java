package com.zhazha.cqhttp.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.bean.Config;
import com.zhazha.cqhttp.chat.ChatEngine;
import com.zhazha.cqhttp.constants.ConfigType;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.repository.ConfigRepository;
import com.zhazha.cqhttp.utils.ReplyUtils;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.MessageVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ChatMessageFilter implements MessageFilter {
    
    public static final String CMD_CHAT_ADD = Constants.CMD_CHAT + "add";
    public static final String CMD_CHAT_GET = Constants.CMD_CHAT + "get";
    public static final String CMD_CHAT_DEL = Constants.CMD_CHAT + "del";
    public static final String CMD_CHAT_LIST = Constants.CMD_CHAT + "list";
    public static final String CMD_CHAT_OL = Constants.CMD_CHAT + "#";
    public static final String CMD_CHAT_HELP = Constants.CMD_CHAT + "help";
    
    @Resource
    private ConfigRepository configRepository;
    @Resource
    private ChatEngine chatEngine;
    
    @Override
    public Boolean match(BaseVO vo) {
        // 强调只能在私聊找执行该指令
        MessageVO messageVO = (MessageVO) vo;
        return StrUtil.startWithIgnoreCase(getRawMessage(messageVO), Constants.CMD_CHAT);
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) {
        MessageVO messageVO = (MessageVO) vo;
        String rawMessage = getRawMessage(messageVO);
        Long userId = messageVO.getUser_id();
        
        if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_ADD)) {
            // 只能为自己添加 api key
            return chatAdd(rawMessage, userId);
        } else if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_GET)) {
            // 只能看自己的
            return chatGet(userId);
        } else if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_DEL)) {
            // 只能删除自己的
            return chatDel(userId.toString());
        } else if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_LIST)) {
            // 列表
            return chatList(userId.toString(), rawMessage);
        } else if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_HELP)) {
            // 列表
            return help();
        } else if (StrUtil.startWithIgnoreCase(rawMessage, CMD_CHAT_OL)) {
            return messageEmit(messageVO, rawMessage);
        }
        return ReplyUtils.build("指令不正确\n");
    }
    
    private ReplyVO messageEmit(MessageVO messageVO, String rawMessage) {
        String msg = rawMessage.replace(CMD_CHAT_OL, "");
        messageVO.setRaw_message(msg);
        String response = chatEngine.execute(messageVO);
        return ReplyVO.builder()
                .at_sender(true)
                .reply(response)
                .build();
    }
    
    private ReplyVO help() {
        return ReplyUtils.build(
                ChatMessageFilter.CMD_CHAT_OL + "你好, openAI\n" +
                        ChatMessageFilter.CMD_CHAT_ADD + " 123456789\n"+
                        ChatMessageFilter.CMD_CHAT_DEL + "\n" +
                        ChatMessageFilter.CMD_CHAT_GET + "\n" +
                        ChatMessageFilter.CMD_CHAT_LIST + "\n" +
                        ChatMessageFilter.CMD_CHAT_HELP
        );
    }
    
    private ReplyVO chatList(String userId, String rawMessage) {
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
                .collect(Collectors.toList())
                .toString();
        return ReplyVO.builder()
                .at_sender(true)
                .reply(s)
                .build();
    }
    
    private ReplyVO chatAdd(String rawMessage, Long userId) {
        String code = rawMessage.replace(CMD_CHAT_ADD, "").trim();
        Config config = configRepository.getById(Constants.ADMIN_QQ);
        if (null == config) {
            config = Config.builder()
                    .id(IdUtil.getSnowflake().nextIdStr())
                    .name(ConfigType.CHAT_NAME)
                    .status(0)
                    .type(ConfigType.CHATGPT.name())
                    .value1(ConfigType.URL)
                    .value2(code)
                    .value3(userId.toString())
                    .build();
        } else {
            config.setValue3(userId.toString());
            config.setStatus(0);
            config.setValue2(code);
        }
        configRepository.saveOrUpdate(config);
        return ReplyVO.builder()
                .at_sender(true)
                .reply("保存成功").build();
    }
    
    private ReplyVO chatDel(String qq) {
        // 只能删除自己的chat sdk
        configRepository.removeByQQ(qq);
        return ReplyVO.builder()
                .reply("删除成功")
                .at_sender(true)
                .build();
    }
    
    private ReplyVO chatGet(Long userId) {
        List<Config> configs = configRepository.listByQQ(userId);
        if (CollUtil.isEmpty(configs)) {
            return ReplyVO.builder()
                    .at_sender(true)
                    .reply("没有配置信息").build();
        }
        String s = configs.stream().map(config ->
                        new Triple(config.getId(), config.getValue1(), config.getValue2())
                )
                .collect(Collectors.toList())
                .toString();
        return ReplyVO.builder()
                .at_sender(true)
                .reply(s).build();
    }
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class Triple {
        private String id;
        private String name;
        private String key;
        
        @Override
        public String toString() {
            return "{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", key='" + key + '\'' +
                    "}\n";
        }
    }
}
