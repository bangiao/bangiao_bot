package com.zhazha.cqhttp.filter;

import cn.hutool.core.text.StrJoiner;
import com.zhazha.cqhttp.constants.CmdAdminEnum;
import com.zhazha.cqhttp.constants.CmdChatEnum;
import com.zhazha.cqhttp.constants.CmdUserEnum;
import com.zhazha.cqhttp.vo.BaseVO;
import com.zhazha.cqhttp.vo.MessageVO;
import com.zhazha.cqhttp.vo.ReplyVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllHelpMessageFilter implements MessageFilter {
    @Override
    public Boolean match(BaseVO vo) {
        MessageVO messageVO = (MessageVO) vo;
        return messageVO.getRaw_message().startsWith("#all");
    }
    
    @Override
    public ReplyVO doFilter(BaseVO vo, MessageFilterChain chain) throws Exception {
        List<String> cmds = new ArrayList<>();
        List<String> c1 = CmdChatEnum.getAllCmd();
        List<String> c2 = CmdAdminEnum.getAllCmd();
        List<String> c3 = CmdUserEnum.getAllCmd();
        cmds.addAll(c1);
        cmds.addAll(c2);
        cmds.addAll(c3);
        return ReplyVO.build(StrJoiner.of("\n").append(cmds).toString());
    }
}
