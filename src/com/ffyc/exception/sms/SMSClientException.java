package com.ffyc.exception.sms;

import com.ffyc.exception.CustomException;

public class SMSClientException extends CustomException
{
    private static final long serialVersionUID = -1599874352582537544L;

    public SMSClientException(String message)
    {
        super(message);
    }
}
