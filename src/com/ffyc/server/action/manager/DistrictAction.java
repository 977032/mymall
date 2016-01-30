package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;

public class DistrictAction 
extends BaseAction
{
  private static final long serialVersionUID = -1060035352162720990L;
  private List list;
  
  public String execute()
    throws Exception
  {
    this.list = this.districtService.findAllRoot();
    return "success";
  }
  
  public List getList()
  {
    return this.list;
  }
  
  public void setList(List list)
  {
    this.list = list;
  }
}
