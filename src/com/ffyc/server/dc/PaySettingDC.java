package com.ffyc.server.dc;

import com.ffyc.server.model.PaySetting;

public class PaySettingDC extends PaySetting{
	private String orderby = "ORDER BY PROPERTY ASC";

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
	
}
