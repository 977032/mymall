package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.dc.OrderDC;
import com.ffyc.server.model.Order;
import com.ffyc.server.utils.PaginationSupport;

public class OrderAction extends BaseAction {

	private static final long serialVersionUID = 5471758931608798890L;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private String oid;
	private Order order;

	public String execute() throws Exception {
		OrderDC dc = new OrderDC();
		dc.setMember(getMemberFromSession().getId());
		dc.setOrderby("ctime desc");
		this.ps = this.orderService.findPageByOrder(dc, this.page,
				this.pagesize);
		return "success";
	}

	public String view() throws Exception {
		this.order = this.orderService.findById(this.oid);
		return "success";
	}

	public String delivered() throws Exception {
		this.order = this.orderService.findById(this.oid);
		this.order.setStatus(Order.STATUS_TRADE_FINISHED);
		this.orderService.update(this.order);
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

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
