package com.ffyc.server.utils.alipay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckURL
{
  public static String check(String urlvalue)
  {
    String inputLine = "";
    try
    {
      URL url = new URL(urlvalue);
      
      HttpURLConnection urlConnection = (HttpURLConnection)url
        .openConnection();
      
      BufferedReader in = new BufferedReader(new InputStreamReader(
        urlConnection.getInputStream()));
      
      inputLine = in.readLine().toString();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    System.out.println(inputLine);
    
    return inputLine;
  }
}
