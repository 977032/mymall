package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.model.Order;
import com.ffyc.server.utils.PaginationSupport;

public class OrderItemAction extends BaseAction {
	private static final long serialVersionUID = 4927408896047700846L;
	private String oid;
	private Order order;
	private PaginationSupport ps;
	private int pagesize = 20;
	private int page = 1;

	public String execute() throws Exception {
		this.order = this.orderService.findById(this.oid);
		OrderItem dc = new OrderItem();
		dc.setMember(getMemberFromSession().getId());
		dc.setOrder(this.order.getId());
		this.ps = this.orderItemService.findPageByOrderItem(dc, page,
				this.pagesize);
		return "success";
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
}
