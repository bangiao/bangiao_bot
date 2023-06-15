package com.zhazha.cqbot.utils;

import com.zhazha.cqbot.constants.Constants;
import lombok.SneakyThrows;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import java.io.File;

public class EmailUtils {
    public static void sendSimpleEmail(String subject, String body, String toEmail) throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName(Constants.HOST_NAME);
        email.setSmtpPort(Constants.HOST_PORT);
        email.setAuthenticator(new DefaultAuthenticator(Constants.FROM_EMAIL, Constants.EMAIL_PWD));
        email.setSSLOnConnect(true);
        email.setFrom(Constants.FROM_EMAIL);
        email.setSubject(subject);
        email.setMsg(body);
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
    public static void exceptionSendEmail(String subject, String body) {
        sendSimpleEmail(subject,body,Constants.toEmail.get(0));
    }

    public static void sendAttachmentEmail(String subject, String body, File file, String toEmail) throws EmailException {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(Constants.HOST_NAME);
        email.setSmtpPort(Constants.HOST_PORT);
        email.setAuthenticator(new DefaultAuthenticator(Constants.FROM_EMAIL, Constants.EMAIL_PWD));
        email.setSSLOnConnect(true);
        email.setFrom(Constants.FROM_EMAIL);
        email.setSubject(subject);
        email.setMsg(body);
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