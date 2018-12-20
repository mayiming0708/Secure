package com.shtel.secure.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * @Author: chenwq
 * @Date: 2018/12/20
 * @Description: 基于中国网建的短信工具类
 */
public class SMSUtil {

    private static Logger logger = LogManager.getLogger(SMSUtil.class.getName());

    /**
     * <p>短信发送接口</p>
     *
     * @param Uid     网建账号
     * @param Key     网建密钥
     * @param smsMob  发送人手机号，批量发送英文，隔开
     * @param smsText 发送内容
     * @return 返回回执信息
     * >0:成功发送短信条数
     * -1:没有该用户
     * -2:接口密钥不正确
     * -3:短信数量不足
     * -4：手机号格式不正确
     * -14:短信内容出现非法字符
     * connect:error:连接创建失败
     * responseFail：响应回执失败
     */
    public static String sendMessage(String Uid, String Key, String smsMob, String smsText) {
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod("http://utf8.api.smschinese.cn");//UTF-编码发送接口地址
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
        NameValuePair[] data = {
                new NameValuePair("Uid", Uid),
                new NameValuePair("Key", Key),
                new NameValuePair("smsMob", smsMob),//发送人手机号xxxxx,xxxxxx批量发送,隔开
                new NameValuePair("smsText", smsText)};//内容
        post.setRequestBody(data);
        try {
            client.executeMethod(post);
        } catch (IOException e) {
            logger.info("SMS短信传输链接创建失败" + e.getMessage());
            return "connect:error";
        }
        String result = null;
        try {
            result = new String(post.getResponseBodyAsString().getBytes("utf-8"));
        } catch (IOException e) {
            result = "responseFail";
            logger.info("SMS服务器回执接受失败" + e.getMessage());
        }
        post.releaseConnection();
        return result;

    }

}
