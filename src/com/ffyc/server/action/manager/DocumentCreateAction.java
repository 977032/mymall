package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Document;

public class DocumentCreateAction 
extends BaseUploadAction
{

  private static final long serialVersionUID = 48917118017887623L;
  private List localelist;
  private List catelist;
  private String c;
  private DocumentCategory doccate;
  private Document document = new Document();
  
  public String execute()
    throws Exception
  {
    this.localelist = this.localeService.findAll();
    this.catelist = this.documentCategoryService.findAllTree();
    if (StringUtils.isNotEmpty(this.c)) {
      this.doccate = this.documentCategoryService.findById(this.c);
    }
    return "success";
  }
  
  public String create()
    throws Exception
  {
    this.document.setId(getUuid());
    this.document.setCtime(getTimestamp());
    this.document.setManagerr(getManagerFromSession());
    this.document.setManager(getManagerFromSession().getId());
    this.document.setVistor(Integer.valueOf(0));
    Attachment attachment = upload();
    if(attachment!=null){
        this.document.setImage(attachment.getId());
        this.document.setImagee(attachment);
    }
    try{
    	this.documentService.save(this.document);
    }catch(Exception e){
    	e.printStackTrace();
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
  
  public Document getDocument()
  {
    return this.document;
  }
  
  public void setDocument(Document document)
  {
    this.document = document;
  }
}

