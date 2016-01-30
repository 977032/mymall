package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Permission;

public class PermissionManagerUpdateAction extends BaseAction {
	private static final long serialVersionUID = -7595255129443069269L;
	private String mgid;
	private List<Permission> permission;

	public String execute() throws Exception {
		Manager manager = this.managerService.findById(this.mgid);
		for (int i = 0; i < this.permission.size(); i++) {
			Permission perm = (Permission) this.permission.get(i);
			perm.setManager(manager.getId());
			String id = existId(manager, perm.getCode());
			if (!id.equals("")) {
				perm.setId(id);
				perm.setPid(perm.getPermission().getId());
				this.permissionService.update(perm);
			} else {
				perm.setId(getUuid());
				perm.setPid(perm.getPermission().getId());
				this.permissionService.save(perm);
			}
		}
		return "success";
	}

	private String existId(Manager manager, String code) throws Exception {
		String id = "";
		List list = this.permissionService.findByManagerAndCode(
				manager.getId(), code);
		if (list.size() > 0) {
			Permission perm = (Permission) list.get(0);
			id = perm.getId();
		}
		return id;
	}

	public String getMgid() {
		return this.mgid;
	}

	public void setMgid(String mgid) {
		this.mgid = mgid;
	}

	public List<Permission> getPermission() {
		return this.permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
}
