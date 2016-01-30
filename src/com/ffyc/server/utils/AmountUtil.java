package com.ffyc.server.utils;

public class AmountUtil
{
  public static final int YUAN_TO_FEN_RATIO = 100;
  
  public static String convertChinese(double money)
  {
    char[] s1 = { 38646, 'Ҽ', 36144, '��', 32902, '��', 38470, '��', '��', '��' };
    char[] s4 = { '��', 35282, 'Ԫ', 'ʰ', '��', 'Ǫ', '��', 'ʰ', '��', 'Ǫ', '��', 'ʰ', '��', 'Ǫ', '��' };
    String str = String.valueOf(Math.round(money * 100.0D));
    String result = "";
    for (int i = 0; i < str.length(); i++)
    {
      int n = str.charAt(str.length() - 1 - i) - '0';
      result = s1[n] + s4[i] + result;
    }
    result = result.replaceAll("��Ǫ", "��");
    result = result.replaceAll("���", "��");
    result = result.replaceAll("��ʰ", "��");
    result = result.replaceAll("����", "��");
    result = result.replaceAll("����", "��");
    result = result.replaceAll("��Ԫ", "Ԫ");
    result = result.replaceAll("���", "��");
    result = result.replaceAll("���", "��");
    
    result = result.replaceAll("����", "��");
    result = result.replaceAll("����", "��");
    result = result.replaceAll("����", "��");
    result = result.replaceAll("����", "��");
    result = result.replaceAll("����", "��");
    result = result.replaceAll("��Ԫ", "Ԫ");
    result = result.replaceAll("����", "��");
    
    result = result.replaceAll("��$", "");
    result = result.replaceAll("Ԫ$", "Ԫ��");
    return result;
  }
}
