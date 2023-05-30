package com.zhazha.cqbot.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "words")
public class Words {
    @TableField(value = "word")
    private String word;

    public static final String COL_WORD = "word";
}