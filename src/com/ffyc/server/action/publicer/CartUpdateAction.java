package com.ffyc.server.action.publicer;

import java.util.Iterator;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.model.Order;

public class CartUpdateAction extends BaseAction {
	
	private static final long serialVersionUID = -8860273483091804555L;
	private OrderItem orderitem;

	public String execute() throws Exception {
		Order order = (Order) this.session.get("order");
		if (order != null) {
			Iterator itr = order.getOrderitems().iterator();
			while (itr.hasNext()) {
				OrderItem oi = (OrderItem) itr.next();
				if (oi.getId().equals(this.orderitem.getId())) {
					oi.setNumber(this.orderitem.getNumber());
					oi.setSubtotal(Double.valueOf(oi.getUnitprice()
							.doubleValue()
							* this.orderitem.getNumber().intValue()));

					oi.setActsum(Double.valueOf(oi.getSubtotal().doubleValue()
							- oi.getDiscount().doubleValue() / 100.0D
							* oi.getSubtotal().doubleValue()));
					this.session.put("order", order);
				}
			}
		}
		return "success";
	}

	public String delorderitem() throws Exception {
		Order order = (Order) this.session.get("order");
		if (order != null) {
			Iterator itr = order.getOrderitems().iterator();
			while (itr.hasNext()) {
				OrderItem oi = (OrderItem) itr.next();
				if (oi.getId().equals(this.orderitem.getId())) {
					itr.remove();
					order.getOrderitems().remove(oi);
					System.out.println("删除订单条目：" + oi.getGoodd().getName());
					this.session.put("order", order);
				}
			}
		}
		return "success";
	}

	public String clear() throws Exception {
		this.session.remove("order");
		return "success";
	}

	public OrderItem getOrderitem() {
		return this.orderitem;
	}

	public void setOrderitem(OrderItem orderitem) {
		this.orderitem = orderitem;
	}
}
