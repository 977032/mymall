package com.ffyc.server.action.member;

import java.util.List;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Address;
import com.ffyc.server.model.Area;
import com.ffyc.server.model.District;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.service.AreaService;
import com.ffyc.server.utils.PaginationSupport;

public class FreightSelAction extends BaseAction {
	private static final long serialVersionUID = 5651732639315304518L;
	private String str;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private List<Area> list;

	public String execute() throws Exception {
		OrderItem dc = new OrderItem();
		dc.setMember(getMemberFromSession().getId());
		dc.setOrder("null");
		this.ps = this.orderItemService.findPageByOrderItem(dc, page, pagesize);

		Address address = this.addressService.findById(this.str);
		if (address != null) {
			District district = address.getDistrictt();
			this.list = district.getAreas();
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

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
