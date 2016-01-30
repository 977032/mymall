package com.ffyc.server.utils;

public class Notice
{
  private boolean status;
  private String code;
  private String title;
  private String body;
  
  public boolean isStatus()
  {
    return this.status;
  }
  
  public void setStatus(boolean status)
  {
    this.status = status;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getBody()
  {
    return this.body;
  }
  
  public void setBody(String body)
  {
    this.body = body;
  }
}
