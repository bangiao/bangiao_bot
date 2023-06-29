package com.zhazha.cqhttp.constants;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    
    /**
     * email
     */
    public static final String URL = "https://api.chatanywhere.com.cn";
    public static final String CMD_USER = "u ";
    public static final String CMD_ADMIN = "a ";
    public static final String CMD_CHAT = "#";
    public static String EMAIL_PWD = "";
    public static final String HOST_NAME = "smtp.qq.com";
    public static final int HOST_PORT = 465;
    public static String ADMIN_QQ;
    public static String BOT_QQ;
    public static final String AT_BOT = "[CQ:at,qq=" + Constants.BOT_QQ + "]";
    public static String FROM_EMAIL = BOT_QQ;
    public static final List<String> toEmail = new ArrayList<>() {{
        add(ADMIN_QQ);
    }};
}
