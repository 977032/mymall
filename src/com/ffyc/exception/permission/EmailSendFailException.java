package com.ffyc.exception.permission;

import com.ffyc.exception.CustomException;

public class EmailSendFailException extends CustomException
{
    private static final long serialVersionUID = -7264251627795191140L;

    public EmailSendFailException(String message)
    {
        super(message);
    }

}
