package com.ffyc.server.common;

import com.ffyc.server.common.ResponseCode;

public class SuccessResponseMessage extends ResponseMessage
{
    public SuccessResponseMessage(Object data)
    {
        this.code = ResponseCode.SUCCESS;
        this.data = data;
        this.message = "Process success";
    }

    public SuccessResponseMessage(String message, Object data)
    {
        this.code = ResponseCode.SUCCESS;
        this.message = message;
        this.data = data;
    }

    public SuccessResponseMessage()
    {
        this.code = ResponseCode.SUCCESS;
        this.message = "Process success";
    }
}
