package com.ffyc.server.model;

public class VerifyCode
{
    private String code;

    private long date;

    public VerifyCode()
    {

    }

    public VerifyCode(String code)
    {
        this.code = code;
        this.date = System.currentTimeMillis();
    }

    public String getCode()
    {
        return code;
    }

    public long getDate()
    {
        return date;
    }
}