package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Logistic;
import com.ffyc.server.utils.PaginationSupport;

public class LogisticAction
extends BaseAction
{
  private static final long serialVersionUID = 9033814980130168971L;
  private PaginationSupport ps;
  private int pagesize = 12;
  private int page = 1;
  private String[] checkbox;
  
  public String execute()
    throws Exception
  {
    Logistic logistic = new Logistic();
    this.ps = this.logisticService.findPageByLogistic(logistic, this.page,this.pagesize);
    return "success";
  }
  
  public String delete()
    throws Exception
  {
	  if ((this.checkbox != null) && (this.checkbox.length > 0)) {
		  for (int i = 0; i < this.checkbox.length; i++)
		    {
		      Logistic logistic = this.logisticService.findById(this.checkbox[i]);
		      if (logistic != null) {
		        this.logisticService.delete(logistic.getId());
		      }
		    }
	  }
    
    return "success";
  }
  
  public PaginationSupport getPs()
  {
    return this.ps;
  }
  
  public void setPs(PaginationSupport ps)
  {
    this.ps = ps;
  }
  
  public int getPagesize()
  {
    return this.pagesize;
  }
  
  public void setPagesize(int pagesize)
  {
    this.pagesize = pagesize;
  }
  
  public int getPage()
  {
    return this.page;
  }
  
  public void setPage(int page)
  {
    this.page = page;
  }
  
  public String[] getCheckbox()
  {
    return this.checkbox;
  }
  
  public void setCheckbox(String[] checkbox)
  {
    this.checkbox = checkbox;
  }
}
