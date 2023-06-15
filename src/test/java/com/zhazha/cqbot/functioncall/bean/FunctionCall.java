package com.zhazha.cqbot.functioncall.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FunctionCall implements Serializable {
    private String name;
    private String description;
    private List<Parameter> parameters;
}