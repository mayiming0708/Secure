package com.shtel.secure.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: chenwq
 * @Date: 2018/12/18
 * @Description: JavaMail工具类
 */
public class EmailTools {
    private static Logger logger = LoggerFactory.getLogger(EmailTools.class);

    /**
     * <p>Mail纯文本发送接口:发送者可批量发送地址以，隔开</p>
     *
     * @param senderAccount    发送者账号
     * @param senderPassWord   发送者密码
     * @param senderAddress    发送者地址
     * @param recipientAddress 收件者地址
     * @param content          邮件内容
     * @param subject          邮件主题
     */
    public static void sendMail(String senderAccount, String senderPassWord, String senderAddress, String recipientAddress, String content, String subject) {
        Session session = initMailConfig();
        Message msg = getMimeMessage(session, senderAddress, recipientAddress, content, subject);
        transportMail(senderAccount, senderPassWord, msg, session);
    }

    /**
     * <p>Mail发送接口支持 纯文本/图片/附件：发送者可批量发送地址以，隔开
     * </p>
     *
     * @param senderAccount    发送者账号
     * @param senderPassWord   发送者密码
     * @param senderAddress    发送者地址
     * @param recipientAddress 收件者地址
     * @param content          邮件内容
     * @param subject          邮件主题
     * @param picURL           图片路径
     * @param attachURL        附件路径
     */
    public static void sendMail(String senderAccount, String senderPassWord, String senderAddress, String recipientAddress, String content, String subject, String picURL, String attachURL) {
        Session session = initMailConfig();
        Message msg = getMimeMessage(session, senderAddress, recipientAddress, content, subject, picURL, attachURL);
        transportMail(senderAccount, senderPassWord, msg, session);
    }

    /**
     * <p>初始化Mail配置</p>
     *
     * @return
     */
    private static Session initMailConfig() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.host", "smtp.163.com");
        Session session = Session.getInstance(properties);
        return session;
    }

    /**
     * <p>Mail传输</p>
     *
     * @param senderAccount  发送者账号
     * @param senderPassWord 发送者账号密码
     * @param msg            发送内容
     * @param session        Mail服务配置
     */
    private static void transportMail(String senderAccount, String senderPassWord, Message msg, Session session) {
        Message message = msg;
        Transport transport = null;
        try {
            transport = session.getTransport();
            transport.connect(senderAccount, senderPassWord);
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("JavaMail传输对象创建失败" + e.getMessage());
        } finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    logger.info("JavaMail传输对象关闭失败" + e.getMessage());
                }
            }
        }
    }

    /**
     * <p>纯文本邮件</p>
     *
     * @param session          邮件环境配置
     * @param senderAddress    发送者邮箱地址
     * @param recipientAddress 收件人邮箱地址
     * @param subject          邮件主题
     * @param content          邮件内容
     * @return
     */
    private static MimeMessage getMimeMessage(Session session, String senderAddress, String recipientAddress, String content, String subject) {
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        try {
            msg.setFrom(new InternetAddress(senderAddress));
            /**
             * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行
             * MimeMessage.RecipientType.TO:发送
             * MimeMessage.RecipientType.CC：抄送
             * MimeMessage.RecipientType.BCC：密送
             */
            msg.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(senderAddress));
            Address[] internetAddressTo = new InternetAddress().parse(recipientAddress);
            msg.setRecipients(MimeMessage.RecipientType.TO, internetAddressTo);
            //设置邮件主题
            msg.setSubject(subject, "UTF-8");
            //设置邮件正文
            msg.setContent(content, "text/html;charset=UTF-8");
            //设置邮件的发送时间,默认立即发送
            msg.setSentDate(new Date());
        } catch (MessagingException e) {
            logger.info("JavaMail:msg对象创建失败:" + e.getMessage());
        }
        return msg;
    }

    /**
     * <p>支持纯文本/附件/图片发送</p>
     *
     * @param session          邮件环境配置
     * @param senderAddress    发送者邮箱地址
     * @param recipientAddress 收件人邮箱地址
     * @param content          邮件内容
     * @param subject          邮件主题
     * @param picURL           上传图片路径
     * @param attachURL        上传附件内容
     * @return
     */
    private static Message getMimeMessage(Session session, String senderAddress, String recipientAddress, String content, String subject, String picURL, String attachURL) {
        MimeMessage mimeMessage = new MimeMessage(session);
        try {
            mimeMessage.setFrom(senderAddress);
            mimeMessage.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress(senderAddress));//同时抄送本地邮箱，进行备份;
            Address[] internetAddressTo = new InternetAddress().parse(recipientAddress);
            mimeMessage.setRecipients(MimeMessage.RecipientType.TO, internetAddressTo);
            mimeMessage.setSubject(subject, "UTF-8");
            //根节点
            MimeMultipart mm = new MimeMultipart();
            if (picURL != null) {
                //媒体节点
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                DataHandler dh = new DataHandler(new FileDataSource(picURL));
                mimeBodyPart.setDataHandler(dh);
                //设置别名
                mimeBodyPart.setContentID("uploadFile");
                //文本节点
                MimeBodyPart text = new MimeBodyPart();
                text.setContent(content + "<br/><img src='cid:uploadFile" +
                        "'/></a>", "text/html;charset=UTF-8");
                //文本与媒体关联节点
                MimeMultipart mm_text_image = new MimeMultipart();
                mm_text_image.addBodyPart(text);
                mm_text_image.addBodyPart(mimeBodyPart);
                mm_text_image.setSubType("related");
                // 将 文本+图片 的混合"节点"封装成一个普通"节点"
                MimeBodyPart text_image = new MimeBodyPart();
                text_image.setContent(mm_text_image);
                mm.addBodyPart(text_image);
            } else {
                //文本节点
                MimeBodyPart text = new MimeBodyPart();
                text.setContent(content, "text/html;charset=UTF-8");
                mm.addBodyPart(text);
            }
            if (attachURL != null) {
                // 9. 创建附件"节点"
                MimeBodyPart attachment = new MimeBodyPart();
                DataHandler dh2 = new DataHandler(new FileDataSource(attachURL));
                attachment.setDataHandler(dh2);
                // 设置附件的文件名（需要编码）
                attachment.setFileName(MimeUtility.encodeText(dh2.getName()));
                mm.addBodyPart(attachment);
            }
            mimeMessage.setContent(mm);
            mimeMessage.setSentDate(new Date());
        } catch (Exception e) {
            logger.info("JavaMail创建message对象失败");
        }
        return mimeMessage;
    }

    public static void main(String[] args) {
        sendMail("kobe_competition@163.com", "QQQ332211", "kobe_competition@163.com", "513545491@qq.com", "简单文本", "激活邮件", "C:\\Users\\cwq\\Desktop\\20181214093503.jpg", "C:\\Users\\cwq\\Desktop\\4D管理内容.xlsx");
    }
}
