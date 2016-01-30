package com.ffyc.server.action.manager;

import java.io.PrintWriter;

import com.ffyc.server.model.PaySetting;

public class PaySettingUpdateAction extends BaseAction {

	private static final long serialVersionUID = 8797822920920670933L;
	private String st;
	private String pvalue;

	public String execute() throws Exception {
		try {
			PaySetting setting = this.paySettingService.findById(this.st);
			setting.setValue(this.pvalue);
			this.paySettingService.update(setting);
			PrintWriter out = this.response.getWriter();
			out.print(this.pvalue);
		} catch (NullPointerException localNullPointerException) {
		}
		return null;
	}

	public String getSt() {
		return this.st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getPvalue() {
		return this.pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}
}
