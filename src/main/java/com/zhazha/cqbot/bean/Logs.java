package com.zhazha.cqbot.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "\"logs\"")
public class Logs {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "event_type")
    private String eventType;

    @TableField(value = "event_source")
    private String eventSource;

    @TableField(value = "description")
    private String description;

    @TableField(value = "create_time")
    private LocalDate createTime;

    public static final String COL_ID = "id";

    public static final String COL_EVENT_TYPE = "event_type";

    public static final String COL_EVENT_SOURCE = "event_source";

    public static final String COL_DESCRIPTION = "description";

    public static final String COL_CREATE_TIME = "create_time";
}