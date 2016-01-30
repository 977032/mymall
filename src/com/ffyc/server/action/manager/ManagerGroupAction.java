package com.ffyc.server.action.manager;

import java.util.List;

public class ManagerGroupAction extends BaseAction {
	private List list;
	private String mgid;

	public String execute() throws Exception {
		this.list = this.managerGroupService.findAll();
		return "success";
	}

	public String delete() throws Exception {
		this.managerGroupService.delete(this.mgid);
		return "success";
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getMgid() {
		return this.mgid;
	}

	public void setMgid(String mgid) {
		this.mgid = mgid;
	}
}
