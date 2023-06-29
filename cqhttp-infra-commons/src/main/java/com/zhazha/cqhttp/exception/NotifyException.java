package com.zhazha.cqhttp.exception;

import com.alibaba.cola.exception.BizException;

public class NotifyException extends BizException {
    public NotifyException(String message) {
        super(message);
    }
}
