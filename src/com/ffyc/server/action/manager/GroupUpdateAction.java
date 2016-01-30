package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Group;

public class GroupUpdateAction extends BaseAction {
	private static final long serialVersionUID = -5249543494087375953L;
	private String gid;
	private Group group;

	public String execute() throws Exception {
		this.group = this.groupService.findById(this.gid);
		return "success";
	}

	public String update() throws Exception {
		Group ug = this.groupService.findById(this.group.getId());
		ug.setName(this.group.getName());
		ug.setDiscount(this.group.getDiscount());
		ug.setIsdefault(this.group.getIsdefault());
		ug.setRequirepoints(this.group.getRequirepoints());
		ug.setRemarks(this.group.getRemarks());
		this.groupService.update(ug);
		return "success";
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGrou(Group group) {
		this.group = group;
	}
}
