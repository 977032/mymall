package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;

public class FreightSetAction extends BaseAction {
	private static final long serialVersionUID = -5184092962188516187L;
	private String str;
	private String ft;

	public String execute() throws Exception {
		System.out.println("str:" + this.str);
		System.out.println("ft:" + this.ft);
		return "success";
	}

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getFt() {
		return this.ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}
}
