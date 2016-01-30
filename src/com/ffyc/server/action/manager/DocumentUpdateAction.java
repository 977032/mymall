package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Document;

public class DocumentUpdateAction 
extends BaseUploadAction
{
  private static final long serialVersionUID = -2147127631817742868L;
  private List localelist;
  private List catelist;
  private String c;
  private DocumentCategory doccate;
  private String d;
  private Document document;
  
  public String execute()
    throws Exception
  {
    this.localelist = this.localeService.findAll();
    this.catelist = this.documentCategoryService.findAllTree();
    this.document = this.documentService.findById(this.d);
    this.document.getDoccatee();
    this.document.getLocalee();
    return "success";
  }
  
  public String update()
    throws Exception
  {
    if (!this.document.getId().equals(""))
    {
      Document udm = this.documentService.findById(this.document.getId());
      udm.setBrief(this.document.getBrief());
      udm.setContent(this.document.getContent());
      udm.setCsort(this.document.getCsort());
      udm.setDoccate(this.document.getDoccatee().getId());
      udm.setLocale(this.document.getLocalee().getId());
      udm.setManager(getManagerFromSession().getId());
      udm.setMarker(this.document.getMarker());
      udm.setTitle(this.document.getTitle());
      
      Attachment attachment = upload();
      Attachment oimage = udm.getImagee();
      if (attachment != null) {
        udm.setImagee(attachment);
        udm.setImage(attachment.getId());
      }
      this.documentService.update(udm);
      if ((attachment != null) && (oimage != null)) {
        this.attachmentService.deleteAndFile(oimage);
      }
    }
    return "success";
  }
  
  public List getLocalelist()
  {
    return this.localelist;
  }
  
  public void setLocalelist(List localelist)
  {
    this.localelist = localelist;
  }
  
  public List getCatelist()
  {
    return this.catelist;
  }
  
  public void setCatelist(List catelist)
  {
    this.catelist = catelist;
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
  
  public String getD()
  {
    return this.d;
  }
  
  public void setD(String d)
  {
    this.d = d;
  }
  
  public Document getDocument()
  {
    return this.document;
  }
  
  public void setDocument(Document document)
  {
    this.document = document;
  }
}
