package com.ffyc.server.action.publicer;

import com.ffyc.server.action.BaseAction;

public class CartAction 
extends BaseAction
{
  private static final long serialVersionUID = -4782570611100734818L;
  
  public String execute()
    throws Exception
  {
    GlobalChannelContent(this.map);
    this.session.put("url", "my_order.htm");
    return "success";
  }
}
