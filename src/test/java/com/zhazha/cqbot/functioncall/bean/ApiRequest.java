package com.zhazha.cqbot.functioncall.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiRequest implements Serializable {
    private String model;
    private List<Message> messages;
    private List<FunctionCall> functions;
}