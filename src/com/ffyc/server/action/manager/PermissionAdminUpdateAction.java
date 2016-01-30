package com.ffyc.server.action.manager;

import com.ffyc.server.model.Permission;
import java.util.List;

public class PermissionAdminUpdateAction extends BaseAction {
	private static final long serialVersionUID = 5616910072081332749L;
	private List permissionroot;
	private String pm;
	private Permission permission;

	public String execute() throws Exception {
		this.permissionroot = this.permissionService.findAllRoot();
		this.permission = this.permissionService.findById(this.pm);
		return "success";
	}

	public String update() throws Exception {
		Permission upm = this.permissionService.findById(this.permission
				.getId());
		upm.setName(this.permission.getName());
		upm.setCode(this.permission.getCode());
		if ((this.permission.getPermission() != null)
				&& (!this.permission.getPermission().getId().equals(""))) {
			upm.setPermission(this.permission.getPermission());
		} else {
			upm.setPermission(null);
		}
		this.permissionService.update(upm);
		return "success";
	}

	public List getPermissionroot() {
		return this.permissionroot;
	}

	public void setPermissionroot(List permissionroot) {
		this.permissionroot = permissionroot;
	}

	public String getPm() {
		return this.pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}
}
