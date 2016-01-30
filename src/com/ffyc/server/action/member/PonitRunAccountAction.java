package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.PointRunAccount;
import com.ffyc.server.utils.PaginationSupport;

public class PonitRunAccountAction 
extends BaseAction
{
  private static final long serialVersionUID = -8524111630027949075L;
  private PaginationSupport ps;
  private int pagesize = 12;
  private int page = 1;
  private Member member;
  
  public String execute()
    throws Exception
  {
    this.member = this.memberService.findById(getMemberFromSession().getId());
    PointRunAccount dc = new PointRunAccount();
    dc.setPoint(this.member.getPoint());
    this.ps = pointRunAccountService.findPageByPointRunAccount(dc, page, pagesize);
    
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
  
  public Member getMember()
  {
    return this.member;
  }
  
  public void setMember(Member member)
  {
    this.member = member;
  }
}

