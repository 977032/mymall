package com.ffyc.server.action.manager;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;

public class ChangePayModeAction extends BaseAction {
	private static final long serialVersionUID = 2882397689106896848L;
	private String pm;
	private String oid;
	private String result;

	public String execute() throws Exception {
		if (StringUtils.isNotEmpty(this.pm) && (this.oid != null)
				&& (!this.oid.equals(""))) {
			PayMode paymode = this.payModeService.findById(this.pm);
			Order order = this.orderService.findById(this.oid);
			if ((paymode != null) && (order != null)) {
				Double cost = Double.valueOf(paymode.getRate().doubleValue()
						/ 100.0D * order.getAmount().doubleValue());
				JSONArray jo = JSONArray.fromObject(cost);
				this.result = jo.toString();
			}
		}
		return "success";
	}

	public String getPm() {
		return this.pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
