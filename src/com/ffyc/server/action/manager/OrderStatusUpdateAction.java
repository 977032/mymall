package com.ffyc.server.action.manager;

import java.util.Iterator;

import com.ffyc.server.model.Good;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.model.Spec;
import com.ffyc.server.utils.alipay.ConfirmSendUrl;

public class OrderStatusUpdateAction extends BaseAction {
	private String oid;

	public String execute() throws Exception {
		Order order = this.orderService.findById(this.oid);

		ConfirmSendUrl csu = new ConfirmSendUrl();
		String result = csu.Confirm(order);

		String status = "";
		status = order.getStatus();
		if (status.equals(Order.STATUS_WAIT_SELLER_SEND_GOODS)) {
			order.setStatus(Order.STATUS_WAIT_BUYER_CONFIRM_GOODS);
			this.orderService.update(order);

			Iterator itr = order.getOrderitems().iterator();
			while (itr.hasNext()) {
				OrderItem oi = (OrderItem) itr.next();
				Integer number = oi.getNumber();
				Spec spec = oi.getSpecc();
				if (spec != null) {
					spec.setInventory(Integer.valueOf(spec.getInventory()
							.intValue() - number.intValue()));
					this.specService.update(spec);
				} else {
					Good good = oi.getGoodd();
					if (good.getInventory().intValue() - number.intValue() >= 0) {
						good.setInventory(Integer.valueOf(good.getInventory()
								.intValue() - number.intValue()));
						this.goodService.update(good);
					}
				}
			}
		}
		return "success";
	}

	public String close() throws Exception {
		Order order = this.orderService.findById(this.oid);
		order.setStatus(Order.STATUS_TRADE_CLOSED);
		this.orderService.update(order);
		return "success";
	}

	public String paid() throws Exception {
		Order order = this.orderService.findById(this.oid);
		order.setStatus(Order.STATUS_WAIT_SELLER_SEND_GOODS);
		this.orderService.update(order);
		return "success";
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
}
