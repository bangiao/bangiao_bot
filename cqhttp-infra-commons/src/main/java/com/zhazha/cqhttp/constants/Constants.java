package com.zhazha.cqhttp.constants;

import java.util.HashSet;
import java.util.Set;

public class Constants {
    
    /**
     * email
     */
    public static final String CMD_USER = "u ";
    public static final String CMD_ADMIN = "a ";
    public static final String CMD_CHAT = "#";
    public static String EMAIL_PWD = "";
    public static final String HOST_NAME = "smtp.qq.com";
    public static final int HOST_PORT = 465;
    public static String ADMIN_QQ;
    public static String BOT_QQ;
    public static String AT_BOT;
    public static String FROM_EMAIL;
    public static Set<String> toEmail = new HashSet<>();
}
