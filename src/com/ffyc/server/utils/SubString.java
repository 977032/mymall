package com.ffyc.server.utils;

import java.io.UnsupportedEncodingException;

public class SubString
{
  public static boolean isLetter(char c)
  {
    int k = 128;
    return c / k == 0;
  }
  
  public static int length(String s)
  {
    if (s == null) {
      return 0;
    }
    char[] c = s.toCharArray();
    int len = 0;
    for (int i = 0; i < c.length; i++)
    {
      len++;
      if (!isLetter(c[i])) {
        len++;
      }
    }
    return len;
  }
  
  public static String substring(String origin, int len, String c)
  {
    if ((origin == null) || (origin.equals("")) || (len < 1)) {
      return "";
    }
    byte[] strByte = new byte[len];
    if (len > length(origin)) {
      return origin + c;
    }
    try
    {
      System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
      int count = 0;
      for (int i = 0; i < len; i++)
      {
        int value = strByte[i];
        if (value < 0) {
          count++;
        }
      }
      if (count % 2 != 0)
      {
        len++;len--;len = len == 1 ? len : len;
      }
      return new String(strByte, 0, len, "GBK") + c;
    }
    catch (UnsupportedEncodingException e)
    {
      throw new RuntimeException(e);
    }
  }
}
