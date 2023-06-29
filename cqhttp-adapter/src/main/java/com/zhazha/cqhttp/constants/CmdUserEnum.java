package com.zhazha.cqhttp.constants;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CmdUserEnum {
    CMD_USER_ADD {
        @Override
        public String getCmd() {
            return Constants.CMD_USER + "add";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    },
    CMD_USER_BLK {
        @Override
        public String getCmd() {
            return Constants.CMD_USER + "block";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    },
    CMD_USER_DEL {
        @Override
        public String getCmd() {
            return Constants.CMD_USER + "del";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    },
    CMD_USER_GET {
        @Override
        public String getCmd() {
            return Constants.CMD_USER + "get";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    },
    CMD_USER_LIST {
        @Override
        public String getCmd() {
            return Constants.CMD_USER + "list";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    },
    CMD_USER_HELP {
        @Override
        public String getCmd() {
            return Constants.CMD_USER + "help";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    };
    
    public abstract String getCmd();
    public abstract Boolean match(String cmd);
    
    public static List<String> getAllCmd() {
        return Arrays.stream(CmdUserEnum.values()).map(CmdUserEnum::getCmd)
                .collect(Collectors.toList());
    }
    
    public static CmdUserEnum matchOne(String cmd) {
        List<CmdUserEnum> itemList = Arrays.stream(CmdUserEnum.values()).filter(cmdUserEnum -> cmdUserEnum.match(cmd)).toList();
        return itemList.isEmpty() ? null : itemList.get(0);
    }
    
}
