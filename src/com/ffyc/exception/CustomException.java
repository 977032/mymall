package com.ffyc.exception;

import com.ffyc.server.common.ResponseCode;

public class CustomException extends Exception 
{
    private static final long serialVersionUID = -8486072460354624317L;

    /** 消息中带的返回编码 */
    protected String code = ResponseCode.FAILURE;

    protected CustomException(String message)
    {
        super(message);
    }

    public String getCode()
    {
        return code;
    }
    
    public CustomException(String code,String message){
    	super(message);
    	this.code = code;
    }
}