package com.ffyc.server.action.publicer;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Category;
import com.ffyc.server.utils.PaginationSupport;

public class IndexAction extends BaseAction {
	private static final long serialVersionUID = 8209153318888999275L;
	private List catelist;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private String c;
	private Category category;
	private String keyword;

	public String execute() throws Exception {
		System.out.println(getDefLocale().getName());
		this.catelist = this.categoryService.findAllTree(getDefLocale());
		StringBuffer sb = new StringBuffer();
	    sb.append("select g.* from tb_good as g where 1=1");
	    
		if (StringUtils.isNotEmpty(this.c)) {
			this.category = this.categoryService.findById(this.c);
			if (this.category != null) {
		    	sb.append(" and ( ");
		 	    sb.append("     g.category ='" + this.category.getId() +"' ");
		 	    sb.append(" or  g.category in ( ");
		 	    sb.append("		     select c.id from tb_category as c  where c.nodepath like  concat('%',"+ this.category.getId() +",'%')"); 
		 	    sb.append("	   )");
		 	    sb.append(" )");
			}
		}
		if (this.keyword != null) {
			sb.append(" and g.name like concat('%',"+ this.keyword +",'%') ");
		}
		String sql = sb.toString();
		String orderby = " order by csort asc, ctime desc ";
		this.goodService.findPageBySql(sql, orderby, page, pagesize);
		return "success";
	}

	public List getCatelist() {
		return this.catelist;
	}

	public void setCatelist(List catelist) {
		this.catelist = catelist;
	}

	public PaginationSupport getPs() {
		return this.ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public int getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
