package com.ffyc.server.common.utils;

public class BooleanTypeExchange
{

    public static String changeTO10(String value)
    {
        if("Y".equalsIgnoreCase(value))
        {
            return "1";
        }
        else if("N".equalsIgnoreCase(value))
        {
            return "0";
        }

        return null;
    }

    public static String changeTOyn(String value)
    {
        if("1".equals(value))
        {
            return "Y";
        }
        else if("0".equalsIgnoreCase(value))
        {
            return "N";
        }

        return null;
    }
}
