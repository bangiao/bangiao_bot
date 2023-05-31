package com.zhazha.cqbot.filter;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.bean.Config;
import com.zhazha.cqbot.constants.Constants;
import com.zhazha.cqbot.controller.vo.BaseVO;
import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;
import com.zhazha.cqbot.service.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class ChatMessageFilter implements MessageFilter {
    
    public static final String CMD_CHAT = "/chat ";
    public static final String CMD_CHAT_ADD = CMD_CHAT + "add ";
    public static final String CMD_CHAT_GET = CMD_CHAT + "get";
    public static final String CMD_CHAT_DEL = CMD_CHAT + "delete ";
    public static final String URL = "https://api.chatanywhere.com.cn";
    
    @Resource
    private ConfigService configService;
    
    @Override
    public Boolean match(BaseVO vo) {
        // 强调只能在私聊找执行该指令
        MessageVO messageVO = (MessageVO) vo;
        String messageType = messageVO.getMessage_type();
        if (!StrUtil.equalsIgnoreCase(messageType, "private")) {
            // 不是私人聊天
            return false;
        }
        // chat add api key
        return StrUtil.startWithAnyIgnoreCase(messageVO.getRaw_message(), CMD_CHAT);
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
        if (!match(vo)) {
            return chain.doChain(vo, chain);
        }
        MessageVO messageVO = (MessageVO) vo;
        String rawMessage = messageVO.getRaw_message();
        Long userId = messageVO.getUser_id();
        
        if (StrUtil.startWithAnyIgnoreCase(rawMessage, CMD_CHAT_ADD)) {
            // 只能为自己添加 api key
            String code = rawMessage.replace(CMD_CHAT_ADD, "").trim();
            Config config = configService.getById(Constants.adminQQ);
            if (null == config) {
                config = Config.builder()
                        .name(URL)
                        .status(0)
                        .type("chatgpt")
                        .value1(URL)
                        .value2(code)
                        .value3(userId.toString())
                        .build();
            } else {
                config.setId(null);
                config.setValue3(userId.toString());
                config.setStatus(0);
                config.setValue2(code);
            }
            // TODO: 2023/6/1 多个 key 查询出给拿去 chatgpt
            configService.saveOrUpdate(config);
            return ReplyVO.builder().reply("保存成功").build();
        } else if (StrUtil.startWithAnyIgnoreCase(rawMessage, CMD_CHAT_GET)) {
            // 只能看自己的
            List<Config> configs = configService.listByQQ(userId);
            String s = configs.stream().map(config ->
                            Triple.of(config.getId(), config.getValue1(), config.getValue2()))
                    .toString();
            return ReplyVO.builder().reply(s).build();
        } else if (StrUtil.startWithAnyIgnoreCase(rawMessage, CMD_CHAT_DEL)) {
            // 只能删除自己的
            String id = rawMessage.replace(CMD_CHAT_DEL, "").trim();
            configService.removeById(id);
            return ReplyVO.builder()
                    .reply("删除成功")
                    .build();
        }
        return null;
    }
}