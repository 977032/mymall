package com.ffyc.server.common;

import com.nationsky.pub.Env;

public class ConfigProperty
{
    /** 假数据开关 */
    public static final boolean TEST_DATA = Boolean.parseBoolean(Env.getProperty("TEST_DATA"));

    /** 列表页面每页项目数量 */
    public static final int LIST_PAGE_SIZE = Integer.parseInt(Env.getProperty("LIST_PAGE_SIZE"));

    /** 用户默认密码 */
    public static final String USER_DEFAULT_PASSWORD = Env.getProperty("USER_DEFAULT_PASSWORD");

    /** 短信平台账号 */
    public static final String SMS_CLIENT_USERNAME = Env.getProperty("SMS_CLIENT_USERNAME");

    /** 短信平台密码 */
    public static final String SMS_CLIENT_PASSWORD = Env.getProperty("SMS_CLIENT_PASSWORD");

    /** 短信平台地址 */
    public static final String SMS_CLIENT_URL = Env.getProperty("SMS_CLIENT_URL");

    /** 短信验证码失效毫秒数 */
    public static final long SMS_VERIFY_CODE_EXPIRE_MILLIS = Long.parseLong(Env.getProperty("SMS_VERIFY_CODE_EXPIRE_MILLIS"));

    /** 重置密码邮件发件人 */
    public static final String PASSWORD_RESET_EMAIL_SENDER = Env.getProperty("PASSWORD_RESET_EMAIL_SENDER");

    /** SMTP发件主机 */
    public static final String SMTP_HOST = Env.getProperty("SMTP_HOST");

    /** Email登录用户名 */
    public static final String EMAIL_USERNAME = Env.getProperty("EMAIL_USERNAME");

    /** Email登录密码 */
    public static final String EMAIL_PASSWORD = Env.getProperty("EMAIL_PASSWORD");

    /** 域名 */
    public static final String DOMAIN = Env.getProperty("DOMAIN");

    /** 重置密码URL前缀 */
    public static final String PASSWORD_RESET_URL_PREFIX = Env.getProperty("PASSWORD_RESET_URL_PREFIX");

    /** 重置密码验证码失效毫秒数 */
    public static final long PASSWORD_RESET_VERIFY_CODE_EXPIRE_MILLIS = Long.parseLong(Env.getProperty("PASSWORD_RESET_VERIFY_CODE_EXPIRE_MILLIS"));

    /** 用户最多开通的课程数量 */
    public static final int MAX_ACTIVE_COLLEGE = Integer.parseInt(Env.getProperty("MAX_ACTIVE_COLLEGE"));

    /** 同行圈进入正式发表所需的投票数 */
    public static final int PEERCICLE_PUBLISH_VOTES = Integer.parseInt(Env.getProperty("PEERCICLE_PUBLISH_VOTES"));

    /** 返回给客户端推荐文章的数量 */
    public static final int RECOMMAND_COUNT = Integer.parseInt(Env.getProperty("RECOMMAND_COUNT"));
    
    /** 应用所在目录*/
    public static final String APP_DIR = Env.getProperty("APP_DIR");

}
