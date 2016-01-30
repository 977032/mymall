package com.ffyc.server.action.manager;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Document;
import com.ffyc.server.utils.PaginationSupport;

public class DocumentAction 
extends BaseAction
{

  private static final long serialVersionUID = 5882129600874637560L;
  private PaginationSupport ps;
  private int pagesize = 20;
  private int page = 1;
  private String c;
  private DocumentCategory doccate;
  private String keyword;
  private String[] checkbox;
  private List catelist;
  
  public String execute()
    throws Exception
  {
    this.catelist = this.documentCategoryService.findAllTree();
    Document dc = new Document();
    if (StringUtils.isNotEmpty(this.keyword)) {
    	dc.setTitle(this.keyword);
    }
    if (StringUtils.isNotEmpty(this.c))
    {
      this.doccate = this.documentCategoryService.findById(this.c);
      if (this.doccate != null) {
    	  dc.setDoccate(this.doccate.getId());
      }
    }
    this.ps = this.documentService.findPageByDocument(dc, page, pagesize);
    return "success";
  }
  
  public String delete()
    throws Exception
  {
	  if ((this.checkbox != null) && (this.checkbox.length > 0)) {
		  for (int i = 0; i < this.checkbox.length; i++)
		    {
		      Document doc = this.documentService.findById(this.checkbox[i]);
		      if (doc != null) {
		        this.documentService.delete(doc.getId());
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
  
  public List getCatelist()
  {
    return this.catelist;
  }
  
  public void setCatelist(List catelist)
  {
    this.catelist = catelist;
  }
}
