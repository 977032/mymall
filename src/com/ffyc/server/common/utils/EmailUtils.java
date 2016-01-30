package com.ffyc.server.common.utils;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.ffyc.server.common.ConfigProperty;

public class EmailUtils
{
    public static final void sendTextEmail(String from, String username, String password, String to, String subject, String content) throws MessagingException
    {
        JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
        // 设定mail server
        senderImpl.setHost(ConfigProperty.SMTP_HOST);

        // 建立邮件消息
        MimeMessage mailMessage = senderImpl.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "UTF-8");

        // 设置收件人，寄件人
        messageHelper.setTo(to);
        messageHelper.setFrom(from);
        messageHelper.setSubject(subject);
        messageHelper.setText(content, true);

        senderImpl.setUsername(username); // 根据自己的情况,设置username
        senderImpl.setPassword(password); // 根据自己的情况, 设置password

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true"); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
        prop.put("mail.smtp.timeout", "25000");
        senderImpl.setJavaMailProperties(prop);
        // 发送邮件
        senderImpl.send(mailMessage);
    }

    public static void main(String[] args) throws MessagingException
    {
        sendTextEmail("oraplen@gmail.com", "oraplen@gmail.com", "daohaozheshiZHU438", "oraplen@qq.com", "test", "<html><head><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /></head><body><h1>重置密码</h1><div><a>http://baidu.com/helasdfkj</a></div></body></html>");
    }
}
