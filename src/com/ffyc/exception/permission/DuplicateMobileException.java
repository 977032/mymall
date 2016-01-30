package com.ffyc.exception.permission;

import com.ffyc.server.common.ResponseCode;
import com.ffyc.exception.CustomException;

public class DuplicateMobileException extends CustomException
{
    private static final long serialVersionUID = 2755179994556758769L;

    private String code = ResponseCode.DUPLICATE_PHONE;

    public DuplicateMobileException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }

}
