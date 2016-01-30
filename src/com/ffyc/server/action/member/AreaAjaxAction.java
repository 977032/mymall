package com.ffyc.server.action.member;

import java.util.Iterator;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Address;
import com.ffyc.server.model.Area;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.OrderItem;

public class AreaAjaxAction extends BaseAction {
	private static final long serialVersionUID = 7149637139110520447L;
	private String str;
	private Address address;
	private List areas;
	private Order order;

	public String execute() throws Exception {
		this.address = this.addressService.findById(this.str);
		if ((this.address != null) && (this.address.getId() != null)
				&& (this.address.getDistrict() != null)) {
			this.areas = this.address.getDistrictt().getAreas();
		} else {
			Area dc = new Area();
			dc.setStatus(Area.STATUS_DEFAULT);
			this.areas = this.areaService.findByArea(dc, null, null);

		}
		this.order = ((Order) this.session.get("order"));
		if ((this.order != null) && (this.order.getOrderitems() != null)) {
			Iterator it = this.order.getOrderitems().iterator();

			Double weight = new Double(0.0D);
			Double amount = new Double(0.0D);
			while (it.hasNext()) {
				OrderItem oi = (OrderItem) it.next();
				if ((oi.getSpec() != null)
						&& (oi.getSpecc().getWeight() != null)) {
					weight = Double.valueOf(weight.doubleValue()
							+ oi.getSpecc().getWeight().doubleValue()
							* oi.getNumber().intValue());
				} else if (oi.getGoodd().getWeight() != null) {
					weight = Double.valueOf(weight.doubleValue()
							+ oi.getGoodd().getWeight().doubleValue()
							* oi.getNumber().intValue());
				}
				amount = Double.valueOf(amount.doubleValue()
						+ oi.getSubtotal().doubleValue());
				this.order.setAmount(amount);
			}
			this.order.setWeight(weight);
		}
		return "success";
	}

	@JSON(serialize = false)
	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@JSON(serialize = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
