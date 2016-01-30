package com.ffyc.server.action.manager;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

import com.ffyc.server.model.Area;
import com.ffyc.server.model.Order;

public class ChangeFreightAction extends BaseAction {

	private static final long serialVersionUID = -9136860245169856424L;
	private String ft;
	private String oid;
	private String result;

	public String execute() throws Exception {
		if (StringUtils.isNotEmpty(this.ft) && (this.oid != null)
				&& (!this.oid.equals(""))) {
			Area area = this.areaService.findById(this.ft);
			Order order = this.orderService.findById(this.oid);
			if ((area != null) && (order != null)) {
				Double cost = area.getLogicost(order.getWeight());
				JSONArray jo = JSONArray.fromObject(cost);
				this.result = jo.toString();
			}
		}
		return "success";
	}

	public String getFt() {
		return this.ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
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
