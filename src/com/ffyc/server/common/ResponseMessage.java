package com.ffyc.server.common;

import java.util.Map;

public class ResponseMessage
{
    /** 请求结果编码 */
    protected String code;

    /** 请求结果描述 */
    protected String message;

    /** 返回的主要数据 */
    protected Object data;

    /** 返回的其他数据 */
    protected Map<String, Object> extraData;

    public ResponseMessage(String code, Object data)
    {
        this.code = code;
        this.data = data;
    }

    public ResponseMessage(String code, Object data, String message)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseMessage()
    {
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public Map<String, Object> getExtraData()
    {
        return extraData;
    }

    public void setExtraData(Map<String, Object> extraData)
    {
        this.extraData = extraData;
    }
}
