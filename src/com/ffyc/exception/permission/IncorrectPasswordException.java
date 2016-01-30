package com.ffyc.exception.permission;

import com.ffyc.server.common.ResponseCode;
import com.ffyc.exception.CustomException;

public class IncorrectPasswordException extends CustomException
{
    private static final long serialVersionUID = 2824542136868562216L;

    private String code = ResponseCode.INCORRECT_PASSWORD;

    public IncorrectPasswordException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }
}
