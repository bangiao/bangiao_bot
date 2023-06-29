package com.zhazha.cqhttp.vo;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqhttp.bean.User;
import com.zhazha.cqhttp.constants.Constants;
import com.zhazha.cqhttp.constants.UserType;
import com.zhazha.cqhttp.exception.NotifyException;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserMessage extends MessageVO {
    /**
     * 检测用户权限
     *
     * @param user 用户
     */
    public void checkUserPermission(User user) {
        if (null == user) {
            throw new NotifyException("你没有权限");
        }
        if (!StrUtil.equalsIgnoreCase(user.getType(), UserType.ADMIN.name())
                && !StrUtil.equalsIgnoreCase(this.getUser_id().toString(), Constants.ADMIN_QQ)) {
            throw new NotifyException("你没有权限");
        }
    }
    
    public String getContentQq(String cmd) {
        String qq = this.getRaw_message().replaceFirst(cmd, "").trim();
        if (StrUtil.isBlank(qq)) {
            throw new NotifyException("没有该qq");
        }
        return qq;
    }
}
