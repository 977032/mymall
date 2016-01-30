package com.ffyc.server.action.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.ffyc.server.model.Group;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.ManagerGroup;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.Permission;

public class PermissionAction extends BaseAction {
	private static final long serialVersionUID = -4994684775683821120L;
	private String gid;
	private Group group;
	private String mbid;
	private Member member;
	private String mgpid;
	private ManagerGroup mgroup;
	private String mgid;
	private Manager manager;
	private List list;

	public String execute() throws Exception {
		this.list = this.permissionService.findAllRoot();
		return "success";
	}

	public String member() throws Exception {
		this.member = this.memberService.findById(this.mbid);
		Permission permission = new Permission();
		permission.setMember(this.mbid);
		this.list = this.permissionService.findByPermission(permission);
		return "success";
	}

	public String manager() throws Exception {
		this.manager = this.managerService.findById(this.mgid);
		Permission permission = new Permission();
		permission.setManager(this.mgid);
		List mgrPmslist = this.permissionService.findByPermission(permission);

		this.list = this.permissionService.findAllRoot();
		for (int k = 0; k < this.list.size(); k++) {
			Permission root = (Permission) this.list.get(k);
			List childList = this.permissionService.findAllChild(root
					.getId());
			List tempList = new ArrayList();
			if (!childList.isEmpty()) {
				Iterator it = childList.iterator();
				while (it.hasNext()) {
					Permission child = (Permission) it.next();
					if (mgrPmslist.size() > 0) {
						boolean flag = true;
						for (int j = 0; j < mgrPmslist.size(); j++) {
							Permission mgrPerm = (Permission) mgrPmslist.get(j);
							if (mgrPerm.getCode().equals(child.getCode())) {
								if (mgrPerm.getPid() != null
										&& child.getPid() != null) {
									if (mgrPerm.getPid().equals(child.getPid())) {
										tempList.add(mgrPerm);
										flag = false;
									}
								}
							}
						}
						if (flag) {
							tempList.add(child);
						}
					} else {
						tempList.addAll(childList);
					}
				}
			}
			root.setPermissions(tempList);
		}
		return "success";
	}

	public String mgroup() throws Exception {
		this.mgroup = this.managerGroupService.findById(this.mgpid);
		Permission permission = new Permission();
		permission.setMgroup(this.mgpid);
		List mgrPmslist = this.permissionService.findByPermission(permission);

		this.list = this.permissionService.findAllRoot();
		for (int k = 0; k < this.list.size(); k++) {
			Permission root = (Permission) this.list.get(k);
			List childList = this.permissionService.findAllChild(root
					.getId());
			List tempList = new ArrayList();
			if (!childList.isEmpty()) {
				Iterator it = childList.iterator();
				while (it.hasNext()) {
					Permission child = (Permission) it.next();
					if (mgrPmslist.size() > 0) {
						boolean flag = true;
						for (int j = 0; j < mgrPmslist.size(); j++) {
							Permission mgrPerm = (Permission) mgrPmslist.get(j);
							if (mgrPerm.getCode().equals(child.getCode())) {
								if (mgrPerm.getPid() != null
										&& child.getPid() != null) {
									if (mgrPerm.getPid().equals(child.getPid())) {
										tempList.add(mgrPerm);
										flag = false;
									}
								}
							}
						}
						if (flag) {
							tempList.add(child);
						}
					} else {
						tempList.addAll(childList);
					}
				}
			}
			root.setPermissions(tempList);
		}
		return "success";
	}

	public String group() throws Exception {
		this.group = this.groupService.findById(this.gid);
		Permission permission = new Permission();
		permission.setGroup(this.gid);
		this.list = this.permissionService.findByPermission(permission);
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

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
