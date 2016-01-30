package com.ffyc.server.common.utils;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.nationsky.pub.log.Log;
import com.nationsky.pub.log.LogFactory;
import com.ffyc.server.common.ConfigProperty;
import com.ffyc.server.common.Language;
import com.ffyc.server.common.utils.SpringContextHolder;
import com.ffyc.exception.sms.SMSClientException;

public class SMSClientUtil
{
    private static Log log = LogFactory.getLog(SMSClientUtil.class);

  
    // private static DictService dictService = new DictServiceImpl();

    /**
     * 发送短信验证码
     * 
     * @param mobile 手机号
     * @return 验证码
     */
    public static String sendSMS(String mobile) throws SMSClientException
    {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(ConfigProperty.SMS_CLIENT_URL);

        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

        int verifyCode = (int)((Math.random() * 9 + 1) * 100000);

        String content = new String("您的验证码是：" + verifyCode + "。请不要把验证码泄露给其他人。");

        NameValuePair[] data = {// 提交短信
        new NameValuePair("account", ConfigProperty.SMS_CLIENT_USERNAME), // 短信平台用户名
        // 短信平台密码，密码可以使用明文密码或使用32位MD5加密
        new NameValuePair("password", ConfigProperty.SMS_CLIENT_PASSWORD), new NameValuePair("mobile", mobile), // 手机号码
        new NameValuePair("content", content),};

        method.setRequestBody(data);

        log.info("SMS verify code prepare to send to: " + mobile + ", verify code is: " + verifyCode);

        String code = null;
        try
        {
            client.executeMethod(method);

            Document doc = DocumentHelper.parseText(method.getResponseBodyAsString());
            Element root = doc.getRootElement();
            code = root.elementText("code");
        }
        catch(HttpException e)
        {
            log.info("SMS verify code send fail to: " + mobile + ", verify code is: " + verifyCode+". HttpException"+e.getMessage());
            throw new SMSClientException("短信服务器通信出错");
        }
        catch(IOException e)
        {
            log.info("SMS verify code send fail to: " + mobile + ", verify code is: " + verifyCode+". IOException"+e.getMessage());
            throw new SMSClientException("服务器通信出错");
        }
        catch(DocumentException e)
        {
            log.info("SMS verify code send fail to: " + mobile + ", verify code is: " + verifyCode+". Dom4jException"+e.getMessage());
            throw new SMSClientException("短信服务器报文出错");
        }

        if("2".equals(code))
        {
            log.info("SMS verify code send success to: " + mobile + ", verify code is: " + verifyCode);
        }
        else
        {
            log.info("SMS verify code send fail(code=" + code + ") to: " + mobile + ", verify code is: " + verifyCode);

            //DictVO dictVO = dictService.getDictItem(Language.zh_CN, DictType.SMS_ERROR, code);
           /* if(dictVO != null)
            {
                throw new SMSClientException(dictVO.getValue());
            }
            else
            {
                throw new SMSClientException("短信服务器出现未知错误，错误代码：" + code);
            }*/
        }

        return String.valueOf(verifyCode);
    }
}