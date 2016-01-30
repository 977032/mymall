package com.ffyc.server.action.manager;

import com.ffyc.server.model.ManagerGroup;

public class ManagerGroupCreateAction extends BaseAction {
	private static final long serialVersionUID = -4586414156693852149L;
	private ManagerGroup mgroup = new ManagerGroup();

	public String execute() throws Exception {
		return "success";
	}

	public String create() throws Exception {
		this.mgroup.setId(getUuid());
		this.managerGroupService.save(this.mgroup);
		return "success";
	}

	public ManagerGroup getMgroup() {
		return mgroup;
	}

	public void setMgroup(ManagerGroup mgroup) {
		this.mgroup = mgroup;
	}

	
}
