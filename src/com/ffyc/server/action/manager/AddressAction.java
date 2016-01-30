package com.ffyc.server.action.manager;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.Address;

public class AddressAction 
extends BaseAction
{
  private static final long serialVersionUID = 258066570351373259L;
  private String mbid;
  private Member member;
  private String[] checkbox;
  
  public String execute()
    throws Exception
  {
    this.member = this.memberService.findById(this.mbid);
    return "success";
  }
  
  public String delete()
    throws Exception
  {
	  if ((this.checkbox != null) && (this.checkbox.length > 0)) {
		  for (int i = 0; i < this.checkbox.length; i++)
		    {
		      Address address = this.addressService.findById(this.checkbox[i]);
		      this.addressService.delete(address.getId());
		  }
	  }
   
    return "success";
  }
  
  public String getMbid()
  {
    return this.mbid;
  }
  
  public void setMbid(String mbid)
  {
    this.mbid = mbid;
  }
  
  public Member getMember()
  {
    return this.member;
  }
  
  public void setMember(Member member)
  {
    this.member = member;
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
