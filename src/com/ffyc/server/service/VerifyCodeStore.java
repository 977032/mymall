package com.ffyc.server.service;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Component;

import com.ffyc.server.common.ConfigProperty;
import com.ffyc.server.common.utils.SpringContextHolder;
import com.ffyc.server.mapper.VerifyCodeMapper;
import com.ffyc.exception.permission.VerifyCodeExpiredException;
import com.ffyc.server.model.VerifyCode;

@Component
public class VerifyCodeStore
{
    private static final String TYPE_REGIST = "000001";

    private static final String TYPE_PASSWORD_RESET = "000002";

    private static final String TYPE_PHONE_MODIFICATION = "000003";

    private static VerifyCodeMapper verifyCodeMapper = SpringContextHolder.getBean(VerifyCodeMapper.class);

    public static void putRegistVerifyCode(String mobile, String verifyCode)
    {
        if(verifyCodeMapper.isExists(TYPE_REGIST, mobile) > 0)
        {
            verifyCodeMapper.update(TYPE_REGIST, mobile, new VerifyCode(verifyCode));
        }
        else
        {
            verifyCodeMapper.insert(TYPE_REGIST, mobile, new VerifyCode(verifyCode));
        }
    }

    public static String getRegistVerifyCode(String mobile) throws VerifyCodeExpiredException
    {
        VerifyCode verifyCode = verifyCodeMapper.get(TYPE_REGIST, mobile);
        if(verifyCode == null)
        {
            throw new VerifyCodeExpiredException("验证码已失效");
        }

        return verifyCode.getCode();
    }

    public static void removeRegistVerifyCode(String mobile)
    {
        verifyCodeMapper.delete(TYPE_REGIST, mobile);
    }

    public static void putMobileModificationVerifyCode(String mobile, String verifyCode)
    {
        if(verifyCodeMapper.isExists(TYPE_PHONE_MODIFICATION, mobile) > 0)
        {
            verifyCodeMapper.update(TYPE_PHONE_MODIFICATION, mobile, new VerifyCode(verifyCode));
        }
        else
        {
            verifyCodeMapper.insert(TYPE_PHONE_MODIFICATION, mobile, new VerifyCode(verifyCode));
        }
    }

    public static String getMobileModificationVerifyCode(String mobile) throws VerifyCodeExpiredException
    {
        VerifyCode verifyCode = verifyCodeMapper.get(TYPE_PHONE_MODIFICATION, mobile);
        if(verifyCode == null)
        {
            throw new VerifyCodeExpiredException("验证码已失效");
        }

        return verifyCode.getCode();
    }

    public static void removeMobileModificationVerifyCode(String mobile)
    {
        verifyCodeMapper.delete(TYPE_PHONE_MODIFICATION, mobile);
    }

    public static void putPasswordResetVerifyCode(String id, String verifyCode)
    {
        if(verifyCodeMapper.isExists(TYPE_PASSWORD_RESET, id) > 0)
        {
            verifyCodeMapper.update(TYPE_PASSWORD_RESET, id, new VerifyCode(verifyCode));
        }
        else
        {
            verifyCodeMapper.insert(TYPE_PASSWORD_RESET, id, new VerifyCode(verifyCode));
        }
    }

    public static String getPasswordResetVerifyCode(String id) throws VerifyCodeExpiredException
    {
        VerifyCode verifyCode = verifyCodeMapper.get(TYPE_PASSWORD_RESET, id);
        if(verifyCode == null)
        {
            throw new VerifyCodeExpiredException("链接已失效，请重新发起");
        }

        return verifyCode.getCode();
    }

    public static void removePasswordResetVerifyCode(String id)
    {
        verifyCodeMapper.delete(TYPE_PASSWORD_RESET, id);
    }

    /**
     * 定时任务，每分钟检查验证码是否存在超过失效时间，失效的就删除掉
     */
    /*
    static
    {
        Timer timer = new Timer();
        
        timer.schedule(new TimerTask()
        {
            private VerifyCodeMapper verifyCodeMapper = SpringContextHolder.getBean(VerifyCodeMapper.class);

            @Override
            public void run()
            {
                long date = System.currentTimeMillis();

                // 注册验证码
                {
                    List<VerifyCode> list = verifyCodeMapper.list(TYPE_REGIST);
                    for(VerifyCode verifyCode: list)
                    {
                        if(verifyCode == null)
                        {
                            continue;
                        }

                        if(date - verifyCode.getDate() >= ConfigProperty.SMS_VERIFY_CODE_EXPIRE_MILLIS)
                        {
                            verifyCodeMapper.delete(TYPE_REGIST, verifyCode.getCode());
                        }
                    }
                }

                // 修改手机号验证码
                {
                    List<VerifyCode> list = verifyCodeMapper.list(TYPE_PHONE_MODIFICATION);
                    for(VerifyCode verifyCode: list)
                    {
                        if(verifyCode == null)
                        {
                            continue;
                        }

                        if(date - verifyCode.getDate() >= ConfigProperty.SMS_VERIFY_CODE_EXPIRE_MILLIS)
                        {
                            verifyCodeMapper.delete(TYPE_REGIST, verifyCode.getCode());
                        }
                    }
                }

                // 重置密码验证码
                {
                    List<VerifyCode> list = verifyCodeMapper.list(TYPE_PASSWORD_RESET);
                    for(VerifyCode verifyCode: list)
                    {
                        if(verifyCode == null)
                        {
                            continue;
                        }

                        if(date - verifyCode.getDate() >= ConfigProperty.PASSWORD_RESET_VERIFY_CODE_EXPIRE_MILLIS)
                        {
                            verifyCodeMapper.delete(TYPE_REGIST, verifyCode.getCode());
                        }
                    }
                }
            }
        }, 60000, 60000);
    }*/
}
