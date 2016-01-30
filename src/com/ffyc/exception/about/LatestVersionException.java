package com.ffyc.exception.about;

import com.ffyc.server.common.ResponseCode;
import com.ffyc.exception.CustomException;

public class LatestVersionException extends CustomException
{

    private static final long serialVersionUID = -3562775338391844245L;

    private String code = ResponseCode.LATEST_APP_VERSION;

    public LatestVersionException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }
}
