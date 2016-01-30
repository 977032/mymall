package com.ffyc.server.vo;

import java.io.Serializable;

public class AppversionVO implements Serializable
{
    private static final long serialVersionUID = -2210683240070774922L;

    private String versionNo;

    private String releaseNote;

    private String url;

    private String createTime;

    public String getVersionNo()
    {
        return versionNo;
    }

    public void setVersionNo(String versionNo)
    {
        this.versionNo = versionNo;
    }

    public String getReleaseNote()
    {
        return releaseNote;
    }

    public void setReleaseNote(String releaseNote)
    {
        this.releaseNote = releaseNote;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

}
