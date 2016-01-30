package com.ffyc.server.action.publicer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Brand;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.utils.PaginationSupport;

public class BrowseAction 
extends BaseAction
{
 private static final long serialVersionUID = -7140811732302806602L;
 private String c = "";
 private Category category;
 private String keyword;
 private String[] ev;
 private ExtraAttrValue eavalue;
 private String bd;
 private Brand brand;
 private PaginationSupport ps;
 private int pagesize = 12;
 private int page = 1;
 private ArrayList<ExtraAttr> extattrSet = new ArrayList();
 private Set<Brand> brandSet = new HashSet();
 
 public String execute()
   throws Exception
 {
   GlobalChannelContent(this.map);
   if (StringUtils.isNotEmpty(this.c)) {
     this.category = this.categoryService.findById(this.c);
   }

   StringBuffer sb= new StringBuffer();
   sb.append("select g.* from tb_good as g where 1=1");
   sb.append(" and (g.status = 'normal' or g.status ='recommand') ");
   
   if (this.category != null)
   {
	   sb.append(" and ( ");
	   sb.append("     g.category ='" + this.category.getId() +"' ");
	   sb.append(" or  g.category in ( ");
	   sb.append("		     select c.id from tb_category as c  where c.nodepath like  concat('%','"+ this.category.getId() +"','%')"); 
	   sb.append("	   )");
	   sb.append(" )");
   }
   if (StringUtils.isNotEmpty(this.keyword)) {
	   sb.append(" and g.name like  concat('%','"+ this.keyword +"','%')");
   }
   if ((this.ev != null) && (!this.ev.equals("")) && (this.ev.length > 0)) {
     if (StringUtils.isNotEmpty(this.ev[0]))
     {
       StringBuffer sbin=new StringBuffer();
       for (int i = 0; i < this.ev.length; i++)
       {
         System.out.println("第" + i + "次");
         if (StringUtils.isNotEmpty(this.ev[i]))
         {
           this.eavalue = this.extraAttrValueService.findById(this.ev[i]);
           if (this.eavalue != null) {
        	 if(sbin.toString().isEmpty()){
        		 sbin.append(this.eavalue.getId());
        	 }else{
        		 sbin.append("," + this.eavalue.getId());
        	 }
           }
         }
       }
       if(!sbin.toString().isEmpty()){
    	   sb.append(" and g.id in (select ge.good from tb_good_extra_attr_value as ge where ge.extraattrvalue in (" + sbin.toString() + ")) ");
       }
     }
   }
   if (StringUtils.isNotEmpty(this.bd))
   {
     this.brand = this.brandService.findById(this.bd);
     if (this.brand != null) {
    	sb.append(" and g.brand ='"+ this.brand.getId() +"' ");
     }
   }
   sb.append(" and g.locale ='"+ getDefLocale().getId() +"' ");
   String orderby = "order by csort asc ";
   try{
     this.ps = this.goodService.findPageBySql(sb.toString(), orderby, this.page, this.pagesize);
   }catch(Exception e){
	   e.printStackTrace();
   }
   
   if ((this.category != null) && (this.category.getCategory() != null) && (this.category.getCategories() != null) && (this.category.getCategories().size() <= 0)) {
     this.category = this.category.getCategory();
   }
   if (this.category != null)
   {
	 sb = null;
     sb = new StringBuffer();
     sb.append(" select c.* from tb_category as c where c.pid ='" +this.category.getId() +"' " );
     sb.append(" or c.pid in (select p.id from tb_category as p where p.nodepath like concat('%','"+ this.category.getId() +"','%'))");
	 
     List list = this.categoryService.findBySql(sb.toString()," order by csort asc");
     for (int i = 0; i < list.size(); i++)
     {
       Category cate = (Category)list.get(i);
       if (cate.getGoodtypee() != null)
       {
         this.brandSet.addAll(cate.getGoodtypee().getBrands());
         List elist =  this.extraAttrService.findAllByGoodType(cate.getGoodtype());
         this.extattrSet.addAll(elist);
       }
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
 
 public String[] getEv()
 {
   return this.ev;
 }
 
 public void setEv(String[] ev)
 {
   this.ev = ev;
 }
 
 public ExtraAttrValue getEavalue()
 {
   return this.eavalue;
 }
 
 public void setEavalue(ExtraAttrValue eavalue)
 {
   this.eavalue = eavalue;
 }
 
 public String getBd()
 {
   return this.bd;
 }
 
 public void setBd(String bd)
 {
   this.bd = bd;
 }
 
 public Brand getBrand()
 {
   return this.brand;
 }
 
 public void setBrand(Brand brand)
 {
   this.brand = brand;
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
 
 public ArrayList<ExtraAttr> getExtattrSet()
 {
   return this.extattrSet;
 }
 
 public void setExtattrSet(ArrayList<ExtraAttr> extattrSet)
 {
   this.extattrSet = extattrSet;
 }
 
 public Set<Brand> getBrandSet()
 {
   return this.brandSet;
 }
 
 public void setBrandSet(Set<Brand> brandSet)
 {
   this.brandSet = brandSet;
 }
}
