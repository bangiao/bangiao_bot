package com.zhazha.cqhttp.constants;

import cn.hutool.core.util.StrUtil;

public enum CmdAdminEnum {
    CMD_ADMIN_ADD {
        @Override
        public String getCmd() {
            return Constants.CMD_ADMIN + "add";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_ADMIN_BLK {
        @Override
        public String getCmd() {
            return Constants.CMD_ADMIN + "block";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_ADMIN_DEL {
        @Override
        public String getCmd() {
            return Constants.CMD_ADMIN + "del";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_ADMIN_GET {
        @Override
        public String getCmd() {
            return Constants.CMD_ADMIN + "get";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    }, CMD_ADMIN_LIST {
        @Override
        public String getCmd() {
            return Constants.CMD_ADMIN + "list";
        }
        
        @Override
        public Boolean match(String cmd) {
            return StrUtil.startWithIgnoreCase(cmd, getCmd());
        }
    };
    
    public abstract String getCmd();
    public abstract Boolean match(String cmd);
}
