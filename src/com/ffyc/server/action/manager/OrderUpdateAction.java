package com.ffyc.server.action.manager;

import java.util.ArrayList;
import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.District;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.PaginationSupport;

public class OrderUpdateAction extends BaseAction {
	private static final long serialVersionUID = 1485444412151447556L;
	private Notice notice = new Notice();
	private String oid;
	private Order order;
	private PaginationSupport ps;
	private int pagesize = 20;
	private int page = 1;
	private List arealist = new ArrayList();
	private List paymodelist;

	public String execute() throws Exception {
		this.order = this.orderService.findById(this.oid);
		this.order.getAreaa();
		OrderItem dc = new OrderItem();
		dc.setOrder(this.order.getId());
		this.ps = this.orderItemService.findPageByOrderItem(dc, page, pagesize);

		District district = this.order.getAddresss().getDistrictt();
		if ((district != null) && (district.getAreas() != null)) {
			this.arealist.addAll(district.getAreas());
		}
		this.paymodelist = this.payModeService.findAll();

		return "success";
	}

	public String update() throws Exception {
		Order uos = this.orderService.findById(this.order.getId());
		if (uos != null) {
			uos.setAmount(this.order.getAmount());
			uos.setArea(this.order.getAreaa().getId());
			uos.setLogicost(this.order.getLogicost());
			uos.setPaycost(this.order.getPaycost());
			uos.setWeight(this.order.getWeight());
			uos.setInvoicetitle(this.order.getInvoicetitle());
			uos.setDuty(this.order.getDuty());
			uos.setDiscount(this.order.getDiscount());
			uos.setActamount(this.order.getActamount());
			uos.setPaymode(this.order.getPaymodee().getId());
			PayMode pm = this.payModeService
					.findById(uos.getPaymodee().getId());
			if (pm.getPmtype().equals(PayMode.PMTYPE_OTHER)) {
				uos.setStatus(this.order.getStatus());
			}
			this.orderService.update(uos);
			setOid(uos.getId());
			return "success";
		}
		this.notice.setStatus(false);
		this.notice.setCode("orders not exist");
		this.notice.setTitle("错误");
		this.notice.setBody("非法请求或该订单不存在");
		return "error";
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
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

	public List getArealist() {
		return this.arealist;
	}

	public void setArealist(List arealist) {
		this.arealist = arealist;
	}

	public List getPaymodelist() {
		return this.paymodelist;
	}

	public void setPaymodelist(List paymodelist) {
		this.paymodelist = paymodelist;
	}
}
