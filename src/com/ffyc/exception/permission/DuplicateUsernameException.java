package com.ffyc.exception.permission;

import com.ffyc.server.common.ResponseCode;
import com.ffyc.exception.CustomException;

public class DuplicateUsernameException extends CustomException
{
    private static final long serialVersionUID = 2764410048106901908L;

    private String code = ResponseCode.DUPLICATE_USERNAME;

    public DuplicateUsernameException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }

}
