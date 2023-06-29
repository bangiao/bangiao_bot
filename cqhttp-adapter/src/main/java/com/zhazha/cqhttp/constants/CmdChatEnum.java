package com.zhazha.cqhttp.constants;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CmdChatEnum {
    
    CMD_CHAT_ADD {
        @Override
        public String getCmd() {
            return Constants.CMD_CHAT + "add";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_CHAT_GET {
        @Override
        public String getCmd() {
            return Constants.CMD_CHAT + "get";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_CHAT_DEL {
        @Override
        public String getCmd() {
            return Constants.CMD_CHAT + "del";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_CHAT_LIST {
        @Override
        public String getCmd() {
            return Constants.CMD_CHAT + "list";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_CHAT_SEND {
        @Override
        public String getCmd() {
            return Constants.CMD_CHAT + "#";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_CHAT_HELP {
        @Override
        public String getCmd() {
            return Constants.CMD_CHAT + "help";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    };

    public abstract String getCmd();
    
    public abstract Boolean match(String cmd);
    
    public static List<String> getAllCmd() {
        return Arrays.stream(CmdChatEnum.values()).map(CmdChatEnum::getCmd)
                .collect(Collectors.toList());
    }
}
