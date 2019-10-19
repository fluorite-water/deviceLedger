package com.wlt.deviceledger.util.common;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * ClassName: MailUtil
 * Describe: TODO
 *
 * @Date: 2019/10/13 18:02
 * @Author: 杨开怀
 */
public class MailUtil {

    private static final String HOST = "";
    private static final Integer PORT = 8080;
    private static final String USERNAME = "";
    private static final String PASSWORD = "";
    private static final String emailForm = "";
    private static final String timeout = "";
    private static final String personal = "";
    private static final String subject = "";
    private static final String html = "请填写下面的激活码激活账号";

    private static JavaMailSenderImpl mailSender = createMailSender();

    private static JavaMailSenderImpl createMailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(HOST);
        sender.setPort(PORT);
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.timeout", timeout);
        p.setProperty("mail.smtp.auth", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    /**
     * 发送邮件
     *
     * @param to 接受人
     * @param subject 主题
     * @param html 发送内容
     * @throws MessagingException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public static void sendMail(String to, String html) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        // 设置utf-8或GBK编码，否则邮件会有乱码
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        messageHelper.setFrom(emailForm, personal);
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(MailUtil.html + html, true);
//      messageHelper.addAttachment("", new File(""));//附件
        mailSender.send(mimeMessage);
    }

}
