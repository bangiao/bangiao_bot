package com.zhazha.cqhttp.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "config")
public class Config {
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @TableField(value = "\"name\"")
    private String name;

    @TableField(value = "value1")
    private String value1;

    @TableField(value = "value2")
    private String value2;

    @TableField(value = "value3")
    private String value3;

    @TableField(value = "\"desc\"")
    private String desc;

    @TableField(value = "\"type\"")
    private String type;

    @TableField(value = "\"status\"")
    private Integer status;

    public static final String COL_ID = "id";

    public static final String COL_NAME = "name";

    public static final String COL_VALUE1 = "value1";

    public static final String COL_VALUE2 = "value2";

    public static final String COL_VALUE3 = "value3";

    public static final String COL_DESC = "desc";

    public static final String COL_TYPE = "type";

    public static final String COL_STATUS = "status";
}