package com.lizikj.common.util;

import com.lizikj.common.enums.HtmlTypeEnum;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 *  旧项目代码
 * Created by liangxiaolin on 2017/9/26.
 */
public class SendMailUtil {
    private static final String SMTP_HOST = "smtp.mxhichina.com";
    private static final String FROM_USER_NAME = "send@lizikj.com";
    //TODO 不建议密码写在代码里
    private static final String FROM_USER_PASSWORD = "A1b2c3d4";

    public SendMailUtil() {
    }

    private static boolean sendMessage(String to, String title, String content, String[] filePaths, String[] bccTo, String[] ccTo, String messageType) throws UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mxhichina.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        try {
            Session mailSession = Session.getInstance(props, new MyAuthenticator("send@lizikj.com", "A1b2c3d4"));
            InternetAddress fromAddress = new InternetAddress("send@lizikj.com");
            InternetAddress toAddress = new InternetAddress(to);
            MimeMessage message = new MimeMessage(mailSession);
            Multipart mailPath = new MimeMultipart();
            message.setFrom(fromAddress);
            message.addRecipient(RecipientType.TO, toAddress);
            InternetAddress[] ccAddress;
            if(bccTo != null && bccTo.length > 0) {
                ccAddress = makeAddress(bccTo);
                message.addRecipients(RecipientType.BCC, ccAddress);
            }

            if(ccTo != null && ccTo.length > 0) {
                ccAddress = makeAddress(ccTo);
                message.addRecipients(RecipientType.CC, ccAddress);
            }

            message.setSentDate(Calendar.getInstance().getTime());
            message.setSubject(title);
            MimeBodyPart contentPath = new MimeBodyPart();
            contentPath.setContent(content, messageType);
            mailPath.addBodyPart(contentPath);
            message.setContent(mailPath);
            if(filePaths != null && filePaths.length > 0) {
                String[] arr$ = filePaths;
                int len$ = filePaths.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    String file = arr$[i$];
                    MimeBodyPart attachmentPath = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(file);
                    attachmentPath.setDataHandler(new DataHandler(fds));
                    if(file.contains("/")) {
                        file = file.substring(file.lastIndexOf("/") + 1);
                    }

                    String fileName = MimeUtility.encodeText(file);
                    attachmentPath.setFileName(fileName);
                    mailPath.addBodyPart(attachmentPath, 0);
                }
            }

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.mxhichina.com", "send@lizikj.com", "A1b2c3d4");
            Transport.send(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static boolean sendMessage(String to, String title, String content, String[] bccTo, String[] ccTo, String messageType, File[] files) throws UnsupportedEncodingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mxhichina.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        try {
            Session mailSession = Session.getInstance(props, new MyAuthenticator("send@lizikj.com", "A1b2c3d4"));
            InternetAddress fromAddress = new InternetAddress("send@lizikj.com");
            InternetAddress toAddress = new InternetAddress(to);
            MimeMessage message = new MimeMessage(mailSession);
            Multipart mailPath = new MimeMultipart();
            message.setFrom(fromAddress);
            message.addRecipient(RecipientType.TO, toAddress);
            InternetAddress[] ccAddress;
            if(bccTo != null && bccTo.length > 0) {
                ccAddress = makeAddress(bccTo);
                message.addRecipients(RecipientType.BCC, ccAddress);
            }

            if(ccTo != null && ccTo.length > 0) {
                ccAddress = makeAddress(ccTo);
                message.addRecipients(RecipientType.CC, ccAddress);
            }

            message.setSentDate(Calendar.getInstance().getTime());
            message.setSubject(title);
            MimeBodyPart contentPath = new MimeBodyPart();
            contentPath.setContent(content, messageType);
            mailPath.addBodyPart(contentPath);
            message.setContent(mailPath);
            if(files != null && files.length > 0) {
                File[] arr$ = files;
                int len$ = files.length;

                for(int i$ = 0; i$ < len$; ++i$) {
                    File file = arr$[i$];
                    MimeBodyPart attachmentPath = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(file);
                    attachmentPath.setDataHandler(new DataHandler(fds));
                    String fileName = "";
                    if(fds.getName().contains("/")) {
                        fileName = fds.getName().substring(fds.getName().lastIndexOf("/") + 1);
                    }

                    fileName = MimeUtility.encodeText(fds.getName());
                    attachmentPath.setFileName(fileName);
                    mailPath.addBodyPart(attachmentPath, 0);
                }
            }

            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.mxhichina.com", "send@lizikj.com", "A1b2c3d4");
            Transport.send(message, message.getAllRecipients());
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static InternetAddress[] makeAddress(String[] addresses) throws AddressException {
        InternetAddress[] ads = null;
        if(addresses != null && addresses.length > 0) {
            ads = new InternetAddress[addresses.length];

            for(int i = 0; i < addresses.length; ++i) {
                InternetAddress address = new InternetAddress(addresses[i]);
                ads[i] = address;
            }
        }

        return ads;
    }

    public static boolean sendMailText(String toMail, String title, String content) throws UnsupportedEncodingException {
        return sendMessage(toMail, title, content, (String[])null, (String[])null, (String[])null, (String) HtmlTypeEnum.HTML_UTF8.getType());
    }

    public static boolean sendMail(String toMail, String title, String content, String[] filePaths) throws UnsupportedEncodingException {
        return sendMessage(toMail, title, content, filePaths, (String[])null, (String[])null, (String)HtmlTypeEnum.HTML_UTF8.getType());
    }

    public static boolean sendMail(String toMail, String title, String content, File[] files) throws  UnsupportedEncodingException {
        return sendMessage(toMail, title, content, (String[])null, (String[])null, (String)HtmlTypeEnum.HTML_UTF8.getType(), (File[])files);
    }

    public static boolean sendMailTextBCC(String toMail, String title, String[] bccMail, String content) throws  UnsupportedEncodingException {
        return sendMessage(toMail, title, content, (String[])null, bccMail, (String[])null, (String)HtmlTypeEnum.HTML_UTF8.getType());
    }

    public static boolean sendMailBCC(String toMail, String title, String[] bccMail, String content, String[] filePaths) throws  UnsupportedEncodingException {
        return sendMessage(toMail, title, content, filePaths, bccMail, (String[])null, (String)HtmlTypeEnum.HTML_UTF8.getType());
    }

    public static boolean sendMailBCC(String toMail, String title, String[] bccMail, String content, File[] files) throws UnsupportedEncodingException {
        return sendMessage(toMail, title, content, bccMail, (String[])null, (String)HtmlTypeEnum.HTML_UTF8.getType(), (File[])files);
    }

    public static boolean sendMailTextCC(String toMail, String title, String[] ccMail, String content) throws  UnsupportedEncodingException {
        return sendMessage(toMail, title, content, (String[])null, (String[])null, (String[])ccMail, (String)HtmlTypeEnum.HTML_UTF8.getType());
    }

    public static boolean sendMailCC(String toMail, String title, String[] ccMail, String content, String[] filePaths) throws  UnsupportedEncodingException {
        return sendMessage(toMail, title, content, filePaths, (String[])null, (String[])ccMail, (String)HtmlTypeEnum.HTML_UTF8.getType());
    }

    public static boolean sendMailCC(String toMail, String title, String[] ccMail, String content, File[] files) throws UnsupportedEncodingException {
        return sendMessage(toMail, title, content, (String[])null, ccMail, (String)HtmlTypeEnum.HTML_UTF8.getType(), (File[])files);
    }

    public static boolean sendMailTextBC(String toMail, String title, String[] bccMail, String[] ccMail, String content) throws  UnsupportedEncodingException {
        return sendMessage(toMail, title, content, (String[])null, bccMail, (String[])ccMail, (String)HtmlTypeEnum.HTML_UTF8.getType());
    }

    public static boolean sendMailBC(String toMail, String title, String[] bccMail, String[] ccMail, String content, File[] files) throws UnsupportedEncodingException {
        sendMessage(toMail, title, content, bccMail, ccMail, HtmlTypeEnum.HTML_UTF8.getType(), files);
        return true;
    }

    public static boolean sendMailBC(String toMail, String title, String[] bccMail, String[] ccMail, String content, String[] filePaths) throws UnsupportedEncodingException {
        return sendMessage(toMail, title, content, filePaths, bccMail, ccMail, HtmlTypeEnum.HTML_UTF8.getType());
    }

    static class  MyAuthenticator extends Authenticator {
        String userName = "";
        String password = "";

        public MyAuthenticator() {
        }

        public MyAuthenticator(String userName, String password) {
            this.userName = userName;
            this.password = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(this.userName, this.password);
        }
    }
}
