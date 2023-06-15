package com.zhazha.cqbot.functioncall.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class Parameter implements Serializable {
    private String name;
    private String type;
    private String description;
}