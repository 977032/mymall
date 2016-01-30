package com.ffyc.server.action.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.common.utils.StringUtil;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.DocumentCategory;

public class DocumentCategoryUpdateAction 
extends BaseUploadAction
{
  private static final long serialVersionUID = 5328087418201741594L;
  private String c;
  private DocumentCategory doccate;
  private List localelist;
  private ArrayList listtree;
  
  public String execute()
    throws Exception
  {
    this.doccate = this.documentCategoryService.findById(this.c);
    this.doccate.getDoccate();
    this.doccate.getImagee();
    this.doccate.getLocalee();
    this.localelist = this.localeService.findAll();
    this.listtree = this.documentCategoryService.findAllTree();
    return "success";
  }
  
  public String update()
    throws Exception
  {
    if (!this.doccate.getId().equals(""))
    {
      DocumentCategory uc = this.documentCategoryService.findById(this.doccate.getId());
      uc.setCsort(this.doccate.getCsort());
      uc.setDetail(this.doccate.getDetail());
      uc.setName(this.doccate.getName());
      uc.setLocale(this.doccate.getLocalee().getId());
      Attachment attachment = upload();
      Attachment oimage = uc.getImagee();
      if (attachment != null) {
        uc.setImagee(attachment);
        uc.setImage(attachment.getId());
      }
      if(StringUtils.isEmpty(this.doccate.getDoccate().getId())){
    	  uc.setPid(null);
      }else{
    	  uc.setPid(this.doccate.getDoccate().getId());
      }
      this.documentCategoryService.update(uc);
      if ((attachment != null) && (oimage != null)) {
        this.attachmentService.delete(oimage.getId());
      }
    }
    return "success";
  }
  
  public String getC()
  {
    return this.c;
  }
  
  public void setC(String c)
  {
    this.c = c;
  }
  
  public DocumentCategory getDoccate()
  {
    return this.doccate;
  }
  
  public void setDoccate(DocumentCategory doccate)
  {
    this.doccate = doccate;
  }
  
  public List getLocalelist()
  {
    return this.localelist;
  }
  
  public void setLocalelist(List localelist)
  {
    this.localelist = localelist;
  }
  
  public ArrayList getListtree()
  {
    return this.listtree;
  }
  
  public void setListtree(ArrayList listtree)
  {
    this.listtree = listtree;
  }
}
