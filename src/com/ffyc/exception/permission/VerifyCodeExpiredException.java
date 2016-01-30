package com.ffyc.exception.permission;

import com.ffyc.server.common.ResponseCode;
import com.ffyc.exception.CustomException;

public class VerifyCodeExpiredException extends CustomException
{
    private static final long serialVersionUID = 4166398129325178743L;

    private String code = ResponseCode.VERIFY_CODE_EXPIRED;

    public VerifyCodeExpiredException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}
