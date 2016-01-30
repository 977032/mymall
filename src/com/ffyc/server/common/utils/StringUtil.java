package com.ffyc.server.common.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nationsky.pub.framework.exception.GenericException;
import com.nationsky.pub.utils.StringUtils;

public class StringUtil
{
    /**
     * 将逗号分隔的字符串转换成String列表
     * 
     * @param stringSet 逗号分隔的字符串
     * @return String列表
     */
    public static final List<String> splitToList(String stringSet)
    {
        try
        {
            return Arrays.asList(StringUtils.split(stringSet, ","));
        }
        catch(GenericException e)
        {
            return null;
        }
    }

    /**
     * 判断两个字符串是否相同
     * 
     * @param str1
     * @param str2
     * @return true，如果相同或两个都为null
     */
    public static boolean isEqual(String str1, String str2)
    {
        if(str1 != null)
        {
            return str1.equals(str2);
        }

        return str1 == str2;
    }
    
    private static Pattern MOBILE_PATTERN = Pattern.compile("^1[3|5|8|7]\\d{9}$");  
    private static Pattern LIKE_MOBILE_PATTERN = Pattern.compile("\\d{11}");  
    
    public static boolean isMobileNO(String mobiles){  
    	  
    	Matcher m = MOBILE_PATTERN.matcher(mobiles);  
    	return m.matches();  
    }  
    
    public static boolean isLikeMobileNO(String mobiles){  
  	  
    	Matcher m = LIKE_MOBILE_PATTERN.matcher(mobiles);  
    	return m.matches();  
    }
    public static String getUUID(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    public static void main(String[] a){
    	System.out.println(isMobileNO("17399889909"));
    	System.out.println(isLikeMobileNO("11223322111"));
    }
}
