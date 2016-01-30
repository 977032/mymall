package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Attachment;

public class AttachmentUpdateAction 
extends BaseAction
{
 private static final long serialVersionUID = 962502724640738488L;
 private String am;
 private Attachment attachment;
 private String gid;
 private String doc;
 
 public String execute()
   throws Exception
 {
   this.attachment = this.attachmentService.findById(this.am);
   return "success";
 }
 
 public String update()
   throws Exception
 {
   Attachment ua = this.attachmentService.findById(this.attachment.getId());
   ua.setDetail(this.attachment.getDetail());
   ua.setUrl(this.attachment.getUrl());
   if ((this.attachment.getStatus() != null) && ((this.attachment.getStatus().equals("default")) || (this.attachment.getStatus().equals(Attachment.STATUS_NORMAL)))) {
     ua.setStatus(this.attachment.getStatus());
   }
   ua.setSort(this.attachment.getSort());
   this.attachmentService.update(ua);
   if (ua.getDocumentt() != null)
   {
     setDoc(ua.getDocumentt().getId());
     return "document";
   }
   setGid(ua.getGoodd().getId());
   return "success";
 }
 
 public String getAm()
 {
   return this.am;
 }
 
 public void setAm(String am)
 {
   this.am = am;
 }
 
 public Attachment getAttachment()
 {
   return this.attachment;
 }
 
 public void setAttachment(Attachment attachment)
 {
   this.attachment = attachment;
 }
 
 public String getGid()
 {
   return this.gid;
 }
 
 public void setGid(String gid)
 {
   this.gid = gid;
 }
 
 public String getDoc()
 {
   return this.doc;
 }
 
 public void setDoc(String doc)
 {
   this.doc = doc;
 }
}
