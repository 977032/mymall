package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.mapper.PermissionMapper;
import com.ffyc.server.utils.BeanFactory;

public class ManagerGroup implements Serializable {

	private String id;
	private String name;
	private String code;
	private String remark;
	private List<Permission> permissions;
	private List<Manager> managers;

	public ManagerGroup() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Permission> getPermissions() {
		if(this.permissions == null){
			PermissionMapper permissionMapper =  BeanFactory.getInstance().getPermissionMapper();
			Permission dc = new Permission();
			dc.setMgroup(id);
			this.permissions = permissionMapper.findByPermission(dc);
		}
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public List<Manager> getManagers() {
		if(this.managers == null) {
			ManagerMapper managerMapper = BeanFactory.getInstance().getManagerMapper();
			Manager dc = new Manager();
			dc.setMgroup(id);
			this.managers = managerMapper.findByManager(dc, null, null);
		}
		return managers;
	}

	public void setManagers(List<Manager> managers) {
		this.managers = managers;
	}

}
