package com.ffyc.exception.permission;

import com.ffyc.server.common.ResponseCode;
import com.ffyc.exception.CustomException;

public class UserNotExistsException extends CustomException
{
    private static final long serialVersionUID = -4886243443732928822L;

    private String code = ResponseCode.USER_NOTE_EXISTS;

    public UserNotExistsException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }
}
