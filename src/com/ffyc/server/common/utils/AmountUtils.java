package com.ffyc.server.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class AmountUtils
{
    /** 金额的格式（保留四位小数，小数第二位四舍五入） */
    private static final DecimalFormat AMOUNT_FORMAT = new DecimalFormat("0.0000");

    /** 金额的格式（不保留小数） */
    private static final DecimalFormat AMOUNT_FORMAT_NO_DOT = new DecimalFormat("0");

    /** 金额的格式（分组并保留四位小数，小数第二位四舍五入） */
    private static final DecimalFormat AMOUNT_FORMAT_WITH_SPLIT = new DecimalFormat("0.0000");

    /** 金额的格式（分组并不保留小数） */
    private static final DecimalFormat AMOUNT_FORMAT_WITH_SPLIT_NO_DOT = new DecimalFormat("0");
    
    /** 金额的格式（保留四位小数，小数第二位四舍五入） */
    private static final DecimalFormat AMOUNT_FORMAT_2 = new DecimalFormat("0.00");
    
    /** 金额的格式（不保留小数） */
    private static final DecimalFormat AMOUNT_FORMAT_NO_DOT_2 = new DecimalFormat("0");
    
    /** 金额的格式（分组并保留四位小数，小数第二位四舍五入） */
    private static final DecimalFormat AMOUNT_FORMAT_WITH_SPLIT_2 = new DecimalFormat("0.00");
    
    /** 金额的格式（分组并不保留小数） */
    private static final DecimalFormat AMOUNT_FORMAT_WITH_SPLIT_NO_DOT_2 = new DecimalFormat("0");

    /*
     * 设定分组格式
     */
    static
    {
        AMOUNT_FORMAT_WITH_SPLIT.setGroupingUsed(true);
        AMOUNT_FORMAT_WITH_SPLIT.setGroupingSize(3);
        AMOUNT_FORMAT_WITH_SPLIT_NO_DOT.setGroupingUsed(true);
        AMOUNT_FORMAT_WITH_SPLIT_NO_DOT.setGroupingSize(3);
    }

    /**
     * 格式化金额，并分组，并指定是否保留小数
     * 
     * @param saveDot 是否保留小数
     * @param d 金额
     * @return 格式化后的金额
     */
    public static final String formatAmount(BigDecimal d, boolean saveDot)
    {
        if(d == null)
        {
            return null;
        }

        if(saveDot)
        {
            return AMOUNT_FORMAT.format(d);
        }
        else
        {
            return AMOUNT_FORMAT_NO_DOT.format(d);
        }
    }

    /**
     * 格式化金额，并分组，并指定是否保留小数
     * 
     * @param saveDot 是否保留小数
     * @param d 金额
     * @return 格式化后的金额
     */
    public static final String formatAmountWithSplit(BigDecimal d, boolean saveDot)
    {
        if(d == null)
        {
            return null;
        }

        if(saveDot)
        {
            return AMOUNT_FORMAT_WITH_SPLIT.format(d);
        }
        else
        {
            return AMOUNT_FORMAT_WITH_SPLIT_NO_DOT.format(d);
        }
    }

    /**
     * 格式化金额，并分组，并指定是否保留小数
     * 
     * @param saveDot 是否保留小数
     * @param d 金额
     * @return 格式化后的金额
     */
    public static final String formatAmount(String d, boolean saveDot)
    {
        if(d == null)
        {
            return null;
        }

        if(saveDot)
        {
            return AMOUNT_FORMAT.format(new BigDecimal(d));
        }
        else
        {
            return AMOUNT_FORMAT_NO_DOT.format(new BigDecimal(d));
        }
    }

    /**
     * 格式化金额，并分组，并指定是否保留小数
     * 
     * @param saveDot 是否保留小数
     * @param d 金额
     * @return 格式化后的金额
     */
    public static final String formatAmountWithSplit(String d, boolean saveDot)
    {
        if(d == null)
        {
            return null;
        }

        if(saveDot)
        {
            return AMOUNT_FORMAT_WITH_SPLIT.format(new BigDecimal(d));
        }
        else
        {
            return AMOUNT_FORMAT_WITH_SPLIT_NO_DOT.format(new BigDecimal(d));
        }
    }
    
    /**
     * 格式化金额，并分组，并指定是否保留小数
     * 
     * @param saveDot 是否保留小数
     * @param d 金额
     * @return 格式化后的金额
     */
    public static final String formatAmount2(BigDecimal d, boolean saveDot)
    {
        if(d == null)
        {
            return null;
        }
        
        if(saveDot)
        {
            return AMOUNT_FORMAT_2.format(d);
        }
        else
        {
            return AMOUNT_FORMAT_NO_DOT_2.format(d);
        }
    }
    
    /**
     * 格式化金额，并分组，并指定是否保留小数
     * 
     * @param saveDot 是否保留小数
     * @param d 金额
     * @return 格式化后的金额
     */
    public static final String formatAmountWithSplit2(BigDecimal d, boolean saveDot)
    {
        if(d == null)
        {
            return null;
        }
        
        if(saveDot)
        {
            return AMOUNT_FORMAT_WITH_SPLIT_2.format(d);
        }
        else
        {
            return AMOUNT_FORMAT_WITH_SPLIT_NO_DOT_2.format(d);
        }
    }
    
    /**
     * 格式化金额，并分组，并指定是否保留小数
     * 
     * @param saveDot 是否保留小数
     * @param d 金额
     * @return 格式化后的金额
     */
    public static final String formatAmount2(String d, boolean saveDot)
    {
        if(d == null)
        {
            return null;
        }
        
        if(saveDot)
        {
            return AMOUNT_FORMAT_2.format(new BigDecimal(d));
        }
        else
        {
            return AMOUNT_FORMAT_NO_DOT_2.format(new BigDecimal(d));
        }
    }
    
    /**
     * 格式化金额，并分组，并指定是否保留小数
     * 
     * @param saveDot 是否保留小数
     * @param d 金额
     * @return 格式化后的金额
     */
    public static final String formatAmountWithSplit2(String d, boolean saveDot)
    {
        if(d == null)
        {
            return null;
        }
        
        if(saveDot)
        {
            return AMOUNT_FORMAT_WITH_SPLIT_2.format(new BigDecimal(d));
        }
        else
        {
            return AMOUNT_FORMAT_WITH_SPLIT_NO_DOT_2.format(new BigDecimal(d));
        }
    }

    public static void main(String[] args)
    {
        BigDecimal d = new BigDecimal("123456789.12");
        System.out.println(AmountUtils.formatAmount(d, false));
    }
}
