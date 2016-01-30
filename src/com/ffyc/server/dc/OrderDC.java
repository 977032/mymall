package com.ffyc.server.dc;

import com.ffyc.server.model.Order;

public class OrderDC extends Order {
	private String orderby = "status desc, ctime desc";

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
}
