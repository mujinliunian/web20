package com.kaishengit.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

    public static void sendSimpleEmail(String subject,String context,String toAddress) {
        SimpleEmail simpleEmail = new SimpleEmail();
        simpleEmail.setHostName("smtp.126.com"); //设置邮件服务器地址
        simpleEmail.setAuthentication("kaishengit","p@ssw@rd");
        simpleEmail.setCharset("UTF-8");
        simpleEmail.setStartTLSEnabled(true);

        try {
            simpleEmail.setFrom("kaishengit@126.com");
            simpleEmail.setSubject(subject);
            simpleEmail.setMsg(context);

            simpleEmail.addTo(toAddress);

            simpleEmail.send();
        } catch (EmailException ex) {
            ex.printStackTrace();
            throw new RuntimeException("给"+toAddress+"发送电子邮件异常",ex);
        }
    }

}
