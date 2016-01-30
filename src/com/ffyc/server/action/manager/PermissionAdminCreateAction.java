package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.model.Group;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.ManagerGroup;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.Permission;

public class PermissionAdminCreateAction extends BaseAction {
	private static final long serialVersionUID = -9126987623757708532L;
	private String gpid;
	private Group group;
	private String mbid;
	private Member member;
	private String mgpid;
	private ManagerGroup mgroup;
	private String mgid;
	private Manager manager;
	private Permission permission = new Permission();
	private List permissionroot;

	public String execute() throws Exception {
		this.permissionroot = this.permissionService.findAllRoot();
		if (StringUtils.isNotEmpty(this.gpid)) {
			this.group = this.groupService.findById(this.gpid);
		}
		if (StringUtils.isNotEmpty(this.mbid)) {
			this.member = this.memberService.findById(this.mbid);
		}
		if (StringUtils.isNotEmpty(this.mgpid)) {
			this.mgroup = this.managerGroupService.findById(this.mgpid);
		}
		if (StringUtils.isNotEmpty(this.mgid)) {
			this.manager = this.managerService.findById(this.mgid);
		}
		return "success";
	}

	public String create() throws Exception {
		this.permission.setId(getUuid());
		if (StringUtils.isNotEmpty(this.gpid)) {
			this.group = this.groupService.findById(this.gpid);
			this.permission.setGroup(this.group.getId());
		} else if (StringUtils.isNotEmpty(this.mbid)) {
			this.member = this.memberService.findById(this.mbid);
			this.permission.setMember(this.member.getId());
		} else if (StringUtils.isNotEmpty(this.mgpid)) {
			this.mgroup = this.managerGroupService.findById(this.mgpid);
			this.permission.setMgroup(this.mgroup.getId());
		} else if (StringUtils.isNotEmpty(this.mgid)) {
			this.manager = this.managerService.findById(this.mgid);
			this.permission.setManager(this.manager.getId());
		}
		if ((this.permission.getPermission() == null)
				|| (this.permission.getPermission().getId().equals(""))) {
			this.permission.setPermission(null);
		}
		this.permissionService.save(this.permission);
		return "success";
	}

	public String getGpid() {
		return this.gpid;
	}

	public void setGpid(String gpid) {
		this.gpid = gpid;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getMbid() {
		return this.mbid;
	}

	public void setMbid(String mbid) {
		this.mbid = mbid;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getMgpid() {
		return this.mgpid;
	}

	public void setMgpid(String mgpid) {
		this.mgpid = mgpid;
	}

	public ManagerGroup getMgroup() {
		return this.mgroup;
	}

	public void setMgroup(ManagerGroup mgroup) {
		this.mgroup = mgroup;
	}

	public String getMgid() {
		return this.mgid;
	}

	public void setMgid(String mgid) {
		this.mgid = mgid;
	}

	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public List getPermissionroot() {
		return this.permissionroot;
	}

	public void setPermissionroot(List permissionroot) {
		this.permissionroot = permissionroot;
	}
}
