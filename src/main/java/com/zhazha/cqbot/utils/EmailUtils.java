package com.zhazha.cqbot.utils;

import com.zhazha.cqbot.constants.Constants;
import lombok.SneakyThrows;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import java.io.File;

public class EmailUtils {
    public static void sendSimpleEmail(String title, String content, String toEmail) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName(Constants.hostName);
        email.setSmtpPort(Constants.hostPort);
        email.setAuthenticator(new DefaultAuthenticator(Constants.fromEmail, Constants.emailPwd));
        email.setSSLOnConnect(true);
        email.setFrom(Constants.fromEmail);
        email.setSubject(title);
        email.setMsg(content);
        if (toEmail == null || toEmail.isEmpty()) {
            for (String recipient : Constants.toEmail) {
                email.addTo(recipient);
            }
        } else {
            email.addTo(toEmail);
        }
        email.send();
    }
    
    @SneakyThrows
    public static void exceptionSendEmail(String title, String content) {
        sendSimpleEmail(title,content,Constants.toEmail.get(0));
    }

    public static void sendAttachmentEmail(String title, String content, File file, String toEmail) throws EmailException {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(Constants.hostName);
        email.setSmtpPort(Constants.hostPort);
        email.setAuthenticator(new DefaultAuthenticator(Constants.fromEmail, Constants.emailPwd));
        email.setSSLOnConnect(true);
        email.setFrom(Constants.fromEmail);
        email.setSubject(title);
        email.setMsg(content);
        if (toEmail == null || toEmail.isEmpty()) {
            for (String recipient : Constants.toEmail) {
                email.addTo(recipient);
            }
        } else {
            email.addTo(toEmail);
        }
        email.attach(file);
        email.send();
    }
}