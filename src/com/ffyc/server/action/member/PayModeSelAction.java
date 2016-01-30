package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.utils.PaginationSupport;
import java.util.List;

public class PayModeSelAction extends BaseAction {

	private static final long serialVersionUID = -3901561379614308128L;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private String str;
	private String ft;
	private List list;

	public String execute() throws Exception {
		OrderItem dc = new OrderItem();
		dc.setMember(getMemberFromSession().getId());
		dc.setOrder("null");
		this.ps = orderItemService.findPageByOrderItem(dc, page, pagesize);
		this.list = this.payModeService.findAll();
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

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getFt() {
		return this.ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
