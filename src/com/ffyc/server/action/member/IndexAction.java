package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;

public class IndexAction 
extends BaseAction
{
  private static final long serialVersionUID = 722295243083353616L;
  
  public String execute()
    throws Exception
  {
    getManagerFromSession();
    return "success";
  }
}