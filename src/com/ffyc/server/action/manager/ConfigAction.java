package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Config;

public class ConfigAction 
extends BaseAction
{
  private static final long serialVersionUID = 5778216668081411564L;
  private List<Config> list;
  private String[] checkbox;
  
  public String execute()
    throws Exception
  {
    this.list = this.configService.findAllRoot();
    return "success";
  }
  
  public String delete()
    throws Exception
  {
	  if ((this.checkbox != null) && (this.checkbox.length > 0)) {
		  for (int i = 0; i < this.checkbox.length; i++)
		    {
		      Config config = this.configService.findById(this.checkbox[i]);
		      if (config != null) {
		        this.configService.delete(config.getId());
		      }
		    }
	  }
    return "success";
  }
  
  public List<Config> getList()
  {
    return this.list;
  }
  
  public void setList(List<Config> list)
  {
    this.list = list;
  }
}

