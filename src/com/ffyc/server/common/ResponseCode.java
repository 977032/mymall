package com.ffyc.server.common;

public class ResponseCode
{
    /** 成功 */
    public static final String SUCCESS = "0x00";

    /** 失败 */
    public static final String FAILURE = "0x99";

    /** 最新App版本 */
    public static final String LATEST_APP_VERSION = "0x01";

    /** 用户名有重复 */
    public static final String DUPLICATE_USERNAME = "0x02";

    /** 手机号码有重复 */
    public static final String DUPLICATE_PHONE = "0x03";

    /** 验证码错误 */
    public static final String INCORRECT_VERIFY_CODE = "0x04";

    /** 用户不存在 */
    public static final String USER_NOTE_EXISTS = "0x05";

    /** 密码错误 */
    public static final String INCORRECT_PASSWORD = "0x06";

    /** 验证码已失效 */
    public static final String VERIFY_CODE_EXPIRED = "0x07";

    /** 验证码无效 */
    public static final String INVALID_ACTIVITION_CODE = "0x08";

    /** 验证码已被使用 */
    public static final String ACTIVITION_CODE_USED = "0x09";

    /** 学院开通数已达上限 */
    public static final String MAX_SIZE_OF_COLLEGE = "0x10";

    /** 学院课程未开通 */
    public static final String COLLEGE_NOT_OPEN = "0x11";

    /** 文件上传失败 */
    public static final String FILE_UPLOAD_FAIL = "0x12";

    /** 资料不完整 */
    public static final String INCOMPLETE_USER_INFO = "0x13";
    
    /** 登陆超时 */
    public static final String TOKEN_EXPIRED = "0x14";
    /** 手机格式不符 */
    public static final String INVALID_PHONE = "0x15";
    /** 重复的三方平台ID */
    public static final String DUPLICATE_PLATFORM_ID = "0x16";
}
