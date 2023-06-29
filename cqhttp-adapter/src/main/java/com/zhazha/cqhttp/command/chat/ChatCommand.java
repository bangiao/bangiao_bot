package com.zhazha.cqhttp.command.chat;

import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.vo.AdminMessage;
import com.zhazha.cqhttp.vo.ReplyVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public interface ChatCommand {
    CmdChatEnum getMode();
    
    ReplyVO execute(AdminMessage adminMessage);
    
    default boolean matches(String rawMessage) {
        return getMode().match(rawMessage);
    }
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Triple {
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
