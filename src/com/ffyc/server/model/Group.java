package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.PermissionMapper;
import com.ffyc.server.utils.BeanFactory;

public class Group implements Serializable {

	private String id;
	private String name;
	private String remarks;
	private Double discount;
	private String isdefault;
	private Integer requirepoints;
	private List<Member> members;
	private List<Permission> permissions;

	public static final String ISDEFAULT_YES = "yes";

	public static final String ISDEFAULT_NO = "yes";

	public Group() {
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

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getIsdefault() {
		return this.isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public Integer getRequirepoints() {
		return this.requirepoints;
	}

	public void setRequirepoints(Integer requirepoints) {
		this.requirepoints = requirepoints;
	}

	public List<Member> getMembers() {
		if(members == null){
			MemberMapper memberMapper = BeanFactory.getInstance().getMemberMapper();
			Member dc = new Member();
			dc.setGroup(id);
			this.members = memberMapper.findByMember(dc, null, null);
		}
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

	public List<Permission> getPermissions() {
		if(this.permissions == null){
			PermissionMapper permissionMapper = BeanFactory.getInstance().getPermissionMapper();
			Permission dc = new Permission();
			dc.setGroup(id);
			this.permissions = permissionMapper.findByPermission(dc);
		}
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
