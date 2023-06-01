package com.zhazha.cqbot.dispatch;

import com.zhazha.cqbot.controller.vo.MessageVO;
import com.zhazha.cqbot.controller.vo.ReplyVO;

public interface IDispatcher {
    ReplyVO dispatch(MessageVO vo) throws Exception;
}
