package com.zhazha.cqhttp.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "words")
public class Words {
    @TableField(value = "word")
    private String word;

    public static final String COL_WORD = "word";
}