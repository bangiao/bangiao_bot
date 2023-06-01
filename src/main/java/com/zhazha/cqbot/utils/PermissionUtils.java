package com.zhazha.cqbot.utils;

import cn.hutool.core.util.StrUtil;
import com.zhazha.cqbot.constants.UserType;

public class PermissionUtils {
    
    public static Boolean isBlockUser(String userType) {
        if (StrUtil.isBlank(userType)) {
            return false;
        }
        return UserType.BLOCK.name().equals(userType);
    }
    
    public static Boolean hasPermission(String source, String target) {
        if (StrUtil.equalsIgnoreCase(source, UserType.BOT.name()) ||
                StrUtil.equalsIgnoreCase(source, UserType.SUPER.name())) {
            return true;
        }
        if (StrUtil.isBlank(source) || StrUtil.isBlank(target)) {
            return false;
        }
        Integer l1 = getPermissionLevel(source);
        Integer l2 = getPermissionLevel(target);
        if (l1 == -1) {
            return false;
        }
        return l1 > l2;
    }
    
    public static Integer getPermissionLevel(String userType) {
        if (StrUtil.isBlank(userType)) {
            return -1;
        }
        if (UserType.USER.name().equals(userType)) {
            return 0;
        }
        if (UserType.ADMIN.name().equals(userType) || UserType.BOT.name().equals(userType)) {
            return 1;
        }
        return -1;
    }
    
}
