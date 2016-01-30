package com.ffyc.server.action.manager;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.Good;
import com.ffyc.server.utils.PaginationSupport;

public class GoodAction 
extends BaseAction
{
  private static final long serialVersionUID = -7197908553183453359L;
  private String od = "";
  private String sc = "desc";
  private PaginationSupport ps;
  private int pagesize = 12;
  private int page = 1;
  private String c;
  private Category category;
  private String keyword;
  private String[] checkbox;
  private List catelist;
  
  public String execute()
    throws Exception
  {
    this.catelist = this.categoryService.findAllTree();
    StringBuffer sb = new StringBuffer();
    StringBuffer sborderby = new StringBuffer();
    sb.append("select g.* from tb_good as g where 1=1");
    sborderby.append(" order by ");
    
    if (StringUtils.isNotEmpty(this.keyword)) {
    	sb.append(" and g.name like concat('%',"+ this.keyword +",'%') ");
    }
    if (StringUtils.isNotEmpty(this.c))
    {
      this.category = this.categoryService.findById(this.c);
      if (this.category != null)
      {
    	sb.append(" and ( ");
 	    sb.append("     g.category ='" + this.category.getId() +"' ");
 	    sb.append(" or  g.category in ( ");
 	    sb.append("		     select c.id from tb_category as c  where c.nodepath like  concat('%',"+ this.category.getId() +",'%')"); 
 	    sb.append("	   )");
 	    sb.append(" )");
      }
    }
    if (StringUtils.isNotEmpty(this.od))
    {
      if (this.sc.equals("asc")) {
    	  sborderby.append(" "+ this.od + " asc ");
      } else {
    	  sborderby.append(" "+ this.od + " desc ");
      }
    }
    else
    {
    	sborderby.append(" ctime desc,utime desc,csort asc");
    }
    try
    {
    	String sql = sb.toString();
    	String orderby = sborderby.toString();
    	this.ps = this.goodService.findPageBySql(sql, orderby, this.page, this.pagesize);
    }
    catch (Exception e)
    {
      sb = new StringBuffer();
      sborderby = new StringBuffer();
      sb.append("select g.* from tb_good as g where 1=1");
      sborderby.append(" order by ctime desc,utime desc,csort asc");
      if (this.keyword != null) {
    	  sb.append(" and g.name like concat('%',"+ this.keyword +",'%') ");
      }
      if (StringUtils.isNotEmpty(this.c))
      {
        this.category = this.categoryService.findById(this.c);
        if (this.category != null) {
        	sb.append(" and g.name like concat('%',"+ this.keyword +",'%') ");
        }
      }
      
      String sql = sb.toString();
  	  String orderby = sborderby.toString();
  	  this.ps = this.goodService.findPageBySql(sql, orderby, this.page, this.pagesize);
    }
    return "success";
  }
  
  public String delete()
    throws Exception
  {
    for (int i = 0; i < this.checkbox.length; i++)
    {
      Good good = this.goodService.findById(this.checkbox[i]);
      this.goodService.delete(good.getId());
    }
    return "success";
  }
  
  public String getOd()
  {
    return this.od;
  }
  
  public void setOd(String od)
  {
    this.od = od;
  }
  
  public String getSc()
  {
    return this.sc;
  }
  
  public void setSc(String sc)
  {
    this.sc = sc;
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
  
  public Category getCategory()
  {
    return this.category;
  }
  
  public void setCategory(Category category)
  {
    this.category = category;
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
