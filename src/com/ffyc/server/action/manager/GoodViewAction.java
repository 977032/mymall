package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Good;

public class GoodViewAction extends BaseAction {

	private static final long serialVersionUID = -8407417168055484031L;
	private String gid;
	private Good good;

	public String execute() throws Exception {
		this.good = this.goodService.findById(this.gid);
		return "success";
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

}
