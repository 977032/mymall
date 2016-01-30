package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.model.ManagerGroup;
import com.ffyc.server.model.Permission;

public class PermissionMgroupUpdateAction extends BaseAction {
	private static final long serialVersionUID = -7595255129443069269L;
	private String mgpid;
	private List<Permission> permission;

	public String execute() throws Exception {
		ManagerGroup mgroup = this.managerGroupService.findById(this.mgpid);
		for (int i = 0; i < this.permission.size(); i++) {
			Permission perm = (Permission) this.permission.get(i);
			perm.setMgroup(mgroup.getId());
			String id = existId(mgroup, perm.getCode());
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

	private String existId(ManagerGroup mgroup, String code) throws Exception {
		String id = "";
		List list = this.permissionService.findByMgroupAndCode(mgroup.getId(),
				code);
		if (list.size() > 0) {
			Permission perm = (Permission) list.get(0);
			id = perm.getId();
		}
		return id;
	}

	public String getMgpid() {
		return this.mgpid;
	}

	public void setMgpid(String mgpid) {
		this.mgpid = mgpid;
	}

	public List<Permission> getPermission() {
		return this.permission;
	}

	public void setPermission(List<Permission> permission) {
		this.permission = permission;
	}
}
