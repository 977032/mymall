package com.ffyc.exception.permission;

import com.ffyc.server.common.ResponseCode;
import com.ffyc.exception.CustomException;

public class IncorrectVerifyCodeException extends CustomException
{

    private static final long serialVersionUID = 363504484000894607L;

    private String code = ResponseCode.INCORRECT_VERIFY_CODE;

    public IncorrectVerifyCodeException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }
}
