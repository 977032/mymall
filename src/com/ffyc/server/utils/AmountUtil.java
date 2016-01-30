package com.ffyc.server.utils;

public class AmountUtil
{
  public static final int YUAN_TO_FEN_RATIO = 100;
  
  public static String convertChinese(double money)
  {
    char[] s1 = { 38646, 'Ò¼', 36144, 'Èþ', 32902, 'Îé', 38470, 'Æâ', '°Æ', '¾Á' };
    char[] s4 = { '·Ö', 35282, 'Ôª', 'Ê°', '°Û', 'Çª', 'Íò', 'Ê°', '°Û', 'Çª', 'ÒÚ', 'Ê°', '°Û', 'Çª', 'Íò' };
    String str = String.valueOf(Math.round(money * 100.0D));
    String result = "";
    for (int i = 0; i < str.length(); i++)
    {
      int n = str.charAt(str.length() - 1 - i) - '0';
      result = s1[n] + s4[i] + result;
    }
    result = result.replaceAll("ÁãÇª", "Áã");
    result = result.replaceAll("Áã°Û", "Áã");
    result = result.replaceAll("ÁãÊ°", "Áã");
    result = result.replaceAll("ÁãÒÚ", "ÒÚ");
    result = result.replaceAll("ÁãÍò", "Íò");
    result = result.replaceAll("ÁãÔª", "Ôª");
    result = result.replaceAll("Áã½Ç", "Áã");
    result = result.replaceAll("Áã·Ö", "Áã");
    
    result = result.replaceAll("ÁãÁã", "Áã");
    result = result.replaceAll("ÁãÒÚ", "ÒÚ");
    result = result.replaceAll("ÁãÁã", "Áã");
    result = result.replaceAll("ÁãÍò", "Íò");
    result = result.replaceAll("ÁãÁã", "Áã");
    result = result.replaceAll("ÁãÔª", "Ôª");
    result = result.replaceAll("ÒÚÍò", "ÒÚ");
    
    result = result.replaceAll("Áã$", "");
    result = result.replaceAll("Ôª$", "ÔªÕû");
    return result;
  }
}
