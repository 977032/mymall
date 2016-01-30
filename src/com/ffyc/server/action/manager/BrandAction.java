package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Brand;
import com.ffyc.server.utils.PaginationSupport;

public class BrandAction extends BaseAction {
	private static final long serialVersionUID = 7117702527638466432L;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private String[] checkbox;

	public String execute() throws Exception {
		Brand dc = new Brand();
		this.ps = this.brandService.findPageByBrand(dc, Integer.valueOf(page),Integer.valueOf(pagesize));
		this.brandService.getLogoos(this.ps.getItems());
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				Brand brand = this.brandService.findById(this.checkbox[i]);
				if (brand != null) {
					this.brandService.delete(brand.getId());
				}
			}
		}
		return "success";
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

	public String[] getCheckbox() {
		return this.checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}
}
