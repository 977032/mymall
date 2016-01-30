package com.ffyc.server.utils.alipay;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UtilDate
{
  public static String getOrderNum()
  {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    return df.format(date);
  }
  
  public static String getDateFormatter()
  {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(date);
  }
  
  public static String getDate()
  {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyyMMdd");
    return df.format(date);
  }
  
  public static String getThree()
  {
    Random rad = new Random();
    return String.valueOf(rad.nextInt(1000));
  }
  
  public static void main(String[] args)
  {
    UtilDate date = new UtilDate();
    System.out.println(getOrderNum());
    System.out.println(getDateFormatter());
    System.out.println(getThree());
  }
}
