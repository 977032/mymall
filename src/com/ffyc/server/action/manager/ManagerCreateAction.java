package com.ffyc.server.action.manager;

import com.ffyc.server.common.utils.MD5Utils;
import com.ffyc.server.model.Manager;

public class ManagerCreateAction extends BaseAction {
	private Manager manager = new Manager();

	public String execute() throws Exception {
		return "success";
	}

	public String create() throws Exception {
		this.manager.setId(getUuid());
		this.manager.setLogincount(Integer.valueOf(0));
		this.manager.setCtime(getTimestamp());
		this.manager.setPassword(MD5Utils.getMD5Str(this.manager.getCpassword()));
		this.managerService.save(this.manager);
		return "success";
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
