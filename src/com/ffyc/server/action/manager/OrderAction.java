package com.ffyc.server.action.manager;

import com.ffyc.server.dc.OrderDC;
import com.ffyc.server.model.Order;
import com.ffyc.server.utils.PaginationSupport;

public class OrderAction extends BaseAction {

	private static final long serialVersionUID = -4317525818076710869L;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private String[] checkbox;

	public String execute() throws Exception {
		OrderDC dc = new OrderDC();
		this.ps = this.orderService.findPageByOrder(dc, this.page,this.pagesize);
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)){
			for (int i = 0; i < this.checkbox.length; i++) {
				Order order = this.orderService.findById(this.checkbox[i]);
				if (order != null) {
					this.orderService.delete(order.getId());
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
