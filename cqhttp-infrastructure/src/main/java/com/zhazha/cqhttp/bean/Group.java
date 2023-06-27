package com.zhazha.cqhttp.bean;

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
@TableName(value = "\"group\"")
public class Group {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "\"name\"")
    private String name;

    @TableField(value = "create_id")
    private String createId;

    @TableField(value = "create_time")
    private LocalDate createTime;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_CREATE_ID = "create_id";

    public static final String COL_CREATE_TIME = "create_time";
}