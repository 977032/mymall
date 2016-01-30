package com.ffyc.server.common.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils
{
    /**
     * 计算年龄
     * 
     * @param birthday 出生日期
     * @return 年龄数
     */
    public static int calculatAge(Date birthday)
    {
        Calendar cal = Calendar.getInstance();

        if(cal.before(birthday))
        {
            return -1;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if(monthNow <= monthBirth)
        {
            if(monthNow == monthBirth)
            {
                if(dayOfMonthNow < dayOfMonthBirth)
                {
                    age--;
                }
            }
            else
            {
                age--;
            }
        }
        return age;
    }
}
