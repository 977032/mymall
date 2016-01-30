package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.OrderItem;

public class CartUpdateAction extends BaseAction {
	private static final long serialVersionUID = 9107968473516943484L;
	private OrderItem orderitem;

	public String execute() throws Exception {
		OrderItem oi = this.orderItemService.findById(this.orderitem.getId());
		oi.setNumber(this.orderitem.getNumber());
		oi.setSubtotal(Double.valueOf(oi.getUnitprice().doubleValue()
				* this.orderitem.getNumber().intValue()));

		oi.setActsum(Double.valueOf(oi.getSubtotal().doubleValue()
				- oi.getDiscount().doubleValue() / 100.0D
				* oi.getSubtotal().doubleValue()));
		this.orderItemService.update(oi);
		return "success";
	}

	public String delorderitem() throws Exception {
		OrderItem oi = this.orderItemService.findById(this.orderitem.getId());
		this.orderItemService.delete(oi.getId());
		return "success";
	}

	public OrderItem getOrderitem() {
		return this.orderitem;
	}

	public void setOrderitem(OrderItem orderitem) {
		this.orderitem = orderitem;
	}
}
