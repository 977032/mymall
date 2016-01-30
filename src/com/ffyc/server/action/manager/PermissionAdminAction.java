package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.model.Permission;

public class PermissionAdminAction extends BaseAction {
	private static final long serialVersionUID = 898743815679074124L;
	private List list;
	private String[] checkbox;

	public String execute() throws Exception {
		this.list = this.permissionService.findAllRoot();
		return "success";
	}

	public String delete() throws Exception {
		for (int i = 0; i < this.checkbox.length; i++) {
			Permission permission = this.permissionService.findById(this.checkbox[i]);
			if (permission != null) {
				this.permissionService.delete(permission.getId());
			}
		}
		return "success";
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String[] getCheckbox() {
		return this.checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}
}
