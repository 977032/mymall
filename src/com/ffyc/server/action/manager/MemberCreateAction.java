package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.common.utils.MD5Utils;
import com.ffyc.server.model.Member;

public class MemberCreateAction 
extends BaseAction
{
  private static final long serialVersionUID = -1546617973287870520L;
  private Member member = new Member();
  
  
  public String execute()
    throws Exception
  {
    return "success";
  }
  
  public String create()
    throws Exception
  {
	this.member.setId(getUuid());
	this.member.setCtype(Member.CTYPE_NORMAL);
	this.member.setLogincount(new Integer(0));
	this.member.setPassword(MD5Utils.getMD5Str(this.member.getCpassword()));
    this.memberService.save(this.member);
    return "success";
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

