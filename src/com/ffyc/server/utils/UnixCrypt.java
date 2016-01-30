package com.ffyc.server.utils;

import java.security.MessageDigest;

public class UnixCrypt
{
  private static final String MAGIC = "$1$";
  private static byte[] finals = new byte[16];
  private static String table = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static char[] intToAscii64 = table.toCharArray();
  
  public static String md5_crypt(String salt, String pw)
  {
    try
    {
      String fix = salt.substring(3);
      if (fix.length() > 8) {
        fix = fix.substring(0, 8);
      }
      int index = fix.indexOf("$");
      if (index != -1) {
        fix = fix.substring(0, index);
      }
      MessageDigest md = MessageDigest.getInstance("MD5");
      MessageDigest md1 = MessageDigest.getInstance("MD5");
      md.reset();
      md.update(pw.getBytes());
      md.update("$1$".getBytes());
      md.update(fix.getBytes());
      
      md1.reset();
      md1.update(pw.getBytes());
      md1.update(fix.getBytes());
      md1.update(pw.getBytes());
      finals = md1.digest();
      for (int i = pw.length(); i > 0; i -= 16)
      {
        int len = i > 16 ? 16 : i;
        md.update(finals, 0, len);
      }
      finals = new byte[16];
      for (int j = pw.length(); j > 0; j >>= 1) {
        if ((j & 0x1) > 0) {
          md.update(finals, 0, 1);
        } else {
          md.update(pw.getBytes(), 0, 1);
        }
      }
      StringBuffer password = new StringBuffer();
      password.append("$1$").append(fix).append("$");
      finals = md.digest();
      for (int k = 0; k < 1000; k++)
      {
        md1.reset();
        if ((k & 0x1) > 0) {
          md1.update(pw.getBytes());
        } else {
          md1.update(finals);
        }
        if (k % 3 > 0) {
          md1.update(fix.getBytes());
        }
        if (k % 7 > 0) {
          md1.update(pw.getBytes());
        }
        if ((k & 0x1) > 0) {
          md1.update(finals);
        } else {
          md1.update(pw.getBytes());
        }
        finals = md1.digest();
      }
      
      //modify
      /*  long pow24 = Math.pow(2.0D, 24.0D);
      long pow16 = Math.pow(2.0D, 16.0D);
      long pow8 = Math.pow(2.0D, 8.0D);
      */
      
      long pow24 = Math.round(Math.pow(2.0D, 24.0D));
      long pow16 = Math.round(Math.pow(2.0D, 16.0D));
      long pow8 = Math.round(Math.pow(2.0D, 8.0D));
      
      long l = ((finals[0] << 16) + pow24) % pow24 | 
        ((finals[6] << 8) + pow16) % pow16 | (finals[12] + pow8) % pow8;
      crypt_i2a64(password, l, 4);
      l = ((finals[1] << 16) + pow24) % pow24 | 
        ((finals[7] << 8) + pow16) % pow16 | (finals[13] + pow8) % pow8;
      crypt_i2a64(password, l, 4);
      l = ((finals[2] << 16) + pow24) % pow24 | 
        ((finals[8] << 8) + pow16) % pow16 | (finals[14] + pow8) % pow8;
      crypt_i2a64(password, l, 4);
      l = ((finals[3] << 16) + pow24) % pow24 | 
        ((finals[9] << 8) + pow16) % pow16 | (finals[15] + pow8) % pow8;
      crypt_i2a64(password, l, 4);
      l = ((finals[4] << 16) + pow24) % pow24 | 
        ((finals[10] << 8) + pow16) % pow16 | (finals[5] + pow8) % pow8;
      crypt_i2a64(password, l, 4);
      l = (finals[11] + pow8) % pow8;
      crypt_i2a64(password, l, 2);
      finals = new byte[16];
      return new String(password);
    }
    catch (Exception e) {}
    return "";
  }
  
  private static void crypt_i2a64(StringBuffer sb, long v, int n)
  {
    do
    {
      sb.append(intToAscii64[((int)(v & 0x3F))]);
      v >>= 6;n--;
    } while (n >= 0);
  }
}
