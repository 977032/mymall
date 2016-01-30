package com.ffyc.server.action.manager;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.model.Member;
import com.ffyc.server.utils.PaginationSupport;

public class MemberAction 
extends BaseAction
{

  private PaginationSupport ps;
  private int pagesize = 3;
  private int page = 1;
  private String keyword;
  private String[] checkbox;
  
  public String execute()
    throws Exception
  {
    Member member = new Member();
    if(StringUtils.isNotEmpty(this.keyword)){
    	member.setAccount(this.keyword);
    }
    this.ps = this.memberService.findPageByMember(member, this.page,this.pagesize);
    return "success";
  }
  
  public String delete()
    throws Exception
  {
	  if ((this.checkbox != null) && (this.checkbox.length > 0)) {
		  for (int i = 0; i < this.checkbox.length; i++)
		    {
		      Member member = this.memberService.findById(this.checkbox[i]);
		      if (member != null) {
		        this.memberService.delete(member.getId());
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
  
  public String getKeyword()
  {
    return this.keyword;
  }
  
  public void setKeyword(String keyword)
  {
    this.keyword = keyword;
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

