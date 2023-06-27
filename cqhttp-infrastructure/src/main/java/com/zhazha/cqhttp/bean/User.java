package com.zhazha.cqhttp.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "\"user\"")
public class User {
    @TableId(value = "qq", type = IdType.AUTO)
    private String qq;

    @TableField(value = "\"name\"")
    private String name;

    @TableField(value = "create_qq")
    private String createQq;

    @TableField(value = "\"type\"")
    private String type;

    public static final String COL_QQ = "qq";

    public static final String COL_NAME = "name";

    public static final String COL_CREATE_QQ = "create_qq";

    public static final String COL_TYPE = "type";
}