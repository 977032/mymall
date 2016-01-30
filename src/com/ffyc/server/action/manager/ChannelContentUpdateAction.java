package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.ChannelContent;
import org.apache.commons.lang.StringUtils;
public class ChannelContentUpdateAction 
extends BaseAction
{
 private static final long serialVersionUID = 694098815459907123L;
 private String ccid;
 private ChannelContent chcontent;
 private List doccates;
 private List categorys;
 private List localelist;
 private String cid;
 
 public String execute()
   throws Exception
 {
   this.chcontent = this.channelContentService.findById(this.ccid);
   this.chcontent.getCategoryy();
   this.chcontent.getChannell();
   this.chcontent.getDoccatee();
   this.chcontent.getDocumentt();
   //this.chcontent.getGoodd();
   //this.chcontent.getManagerr();
   this.chcontent.getLocalee();
   this.doccates = this.documentCategoryService.findAllTree();
   this.categorys = this.categoryService.findAllTree();
   this.localelist = this.localeService.findAll();
   if ((this.chcontent.getGlobal() != null) && (this.chcontent.getGlobal().equals("yes"))) {
     return "global";
   }
   return "success";
 }
 
 public String update()
   throws Exception
 {
   ChannelContent uct = this.channelContentService.findById(this.chcontent.getId());
   uct.setDetail(this.chcontent.getDetail());
   if (this.chcontent.getCategoryy()!=null && StringUtils.isNotEmpty(this.chcontent.getCategoryy().getId())) {
     uct.setCategory(this.chcontent.getCategoryy().getId());
   } else {
     uct.setCategory(null);
   }
   if (this.chcontent.getDoccatee() != null && StringUtils.isNotEmpty(this.chcontent.getDoccatee().getId())) {
     uct.setDoccate(this.chcontent.getDoccatee().getId());
   } else {
     uct.setDoccate(null);
   }
   if (this.chcontent.getDocumentt() != null && StringUtils.isNotEmpty(this.chcontent.getDocumentt().getId())) {
     uct.setDocument(this.chcontent.getDocumentt().getId());
   } else {
     uct.setDocument(null);
   }
   uct.setLocale(this.chcontent.getLocalee().getId());
   uct.setManager(getManagerFromSession().getId());
   uct.setMarker(this.chcontent.getMarker());
   uct.setNumber(this.chcontent.getNumber());
   uct.setOffset(this.chcontent.getOffset());
   uct.setType(this.chcontent.getType());
   uct.setKeys(this.chcontent.getKeys());
   try{
   this.channelContentService.update(uct);
   }catch(Exception e){
	   e.printStackTrace();
   }
   if ((uct.getGlobal() != null) && (uct.getGlobal().equals("yes"))) {
     return "global";
   }
   setCa(uct.getChannell().getId());
   return "success";
 }
 
 public String getCcid()
 {
   return this.ccid;
 }
 
 public void setCcid(String ccid)
 {
   this.ccid = ccid;
 }
 
 public ChannelContent getChcontent()
 {
   return this.chcontent;
 }
 
 public void setChcontent(ChannelContent chcontent)
 {
   this.chcontent = chcontent;
 }
 
 public List getDoccates()
 {
   return this.doccates;
 }
 
 public void setDoccates(List doccates)
 {
   this.doccates = doccates;
 }
 
 public List getCategorys()
 {
   return this.categorys;
 }
 
 public void setCategorys(List categorys)
 {
   this.categorys = categorys;
 }
 
 public List getLocalelist()
 {
   return this.localelist;
 }
 
 public void setLocalelist(List localelist)
 {
   this.localelist = localelist;
 }
 
 public String getCid()
 {
   return this.cid;
 }
 
 public void setCa(String cid)
 {
   this.cid = cid;
 }
}
