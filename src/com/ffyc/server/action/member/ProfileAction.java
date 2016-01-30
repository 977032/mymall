package com.ffyc.server.action.member;

import java.util.List;
import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Member;

public class ProfileAction 
extends BaseUploadAction
{
  private static final long serialVersionUID = 3229810347477162110L;
  private List districtroot;
  private Member member;
  
  public String execute()
    throws Exception
  {
    this.member = this.memberService.findById(getMemberFromSession().getId());
    this.member.getImagee();
    if(this.member.getDistrictt()!=null){
    	this.member.getDistrictt().getDistrict();
    }
    return "success";
  }
  
  public String edit()
    throws Exception
  {
    this.member = this.memberService.findById(getMemberFromSession().getId());
    this.districtroot = this.districtService.findAllRoot();
    return "success";
  }
  
  public String update()
    throws Exception
  {
    Member um = this.memberService.findById(getMemberFromSession().getId());
    um.setAddress(this.member.getAddress());
    if (this.member.getDistrictt() != null) {
      um.setDistrict(String.valueOf(this.member.getDistrictt().getId())); 
    }
    um.setIdcard(this.member.getIdcard());
    
    um.setWechat(this.member.getWechat());
    
    um.setNickname(this.member.getNickname());
    um.setPostcode(this.member.getPostcode());
    um.setEmail(this.member.getEmail());
    um.setTelephone(this.member.getTelephone());
    Attachment attachment = upload();
    Attachment oimage = um.getImagee();
    if (attachment != null) {
      um.setImagee(attachment);
      um.setImage(attachment.getId());
    }
    this.memberService.update(um);
    if ((attachment != null) && (oimage != null)) {
      this.attachmentService.delete(oimage.getId());
    }
    return "success";
  }
  
  public List getDistrictroot()
  {
    return this.districtroot;
  }
  
  public void setDistrictroot(List districtroot)
  {
    this.districtroot = districtroot;
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
