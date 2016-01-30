package com.ffyc.server.common;

public class PaginationData
{
    /** 数据 */
    private Object data;

    /** 页码计算器 */
    private PageCalc pageCalc;

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public PageCalc getPageCalc()
    {
        return pageCalc;
    }

    public void setPageCalc(PageCalc pageCalc)
    {
        this.pageCalc = pageCalc;
    }

}
