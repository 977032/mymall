package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.utils.PaginationSupport;

public class CartAction extends BaseAction {
	
	private static final long serialVersionUID = 2057754035724962949L;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private String[] checkbox;

	public String execute() throws Exception {
		int startIndex = 0;
		startIndex = (this.page - 1) * this.pagesize;
		OrderItem dc = new OrderItem();
		dc.setMember(getMemberFromSession().getId());
		dc.setOrder("null");
		this.ps = orderItemService.findPageByOrderItem(dc, page, pagesize);
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
