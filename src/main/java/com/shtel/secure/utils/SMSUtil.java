package com.shtel.secure.utils;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
     */
    public static String sendMessage(String Uid, String Key, String smsMob, String smsText) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://utf8.api.smschinese.cn");//UTF-编码发送接口地址
        post.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//在头文件中设置转码
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("Uid", Uid));
        formparams.add(new BasicNameValuePair("Key", Key));
        formparams.add(new BasicNameValuePair("smsMob", smsMob));
        formparams.add(new BasicNameValuePair("smsText", smsText));
        String result = null;
        try {
            post.setEntity(new UrlEncodedFormEntity(formparams, "utf-8"));//设置参数编码格式
            CloseableHttpResponse response = httpClient.execute(post);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            logger.info("SMS短信传输链接创建失败" + e.getMessage());
            return "connect:error";
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                logger.info("SMS短信传输链接关闭失败" + e.getMessage());
            }
            post.releaseConnection();
        }
        System.out.println(result); //打印返回消息状态
        return result;
    }

}
