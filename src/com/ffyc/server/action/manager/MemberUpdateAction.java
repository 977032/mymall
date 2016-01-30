package com.ffyc.server.action.manager;

import com.ffyc.server.model.Group;
import com.ffyc.server.model.Member;
import java.util.List;

public class MemberUpdateAction extends BaseAction {
	private static final long serialVersionUID = 7688332252384939404L;
	private List groupslist;
	private String mbid;
	private Member member;

	public String execute() throws Exception {
		this.groupslist = this.groupService.findAll();
		this.member = this.memberService.findById(this.mbid);
		this.member.getGroupp();
		return "success";
	}

	public String update() throws Exception {
		Member um = this.memberService.findById(this.member.getId());
		if ((this.member.getPassword() != null)
				&& (!this.member.getPassword().equals(""))) {
			um.setPassword(this.member.getPassword());
		}
		um.setEmail(this.member.getEmail());
		um.setName(this.member.getName());
		if ((this.member.getGroupp().getId() != null)
				&& (!this.member.getGroupp().getId().equals(""))) {
			um.setGroup(this.member.getGroupp().getId());
		}else{
			um.setGroup(null);
		}
		um.setNickname(this.member.getNickname());
		um.setIdcard(this.member.getIdcard());
		um.setTelephone(this.member.getTelephone());
		um.setMobile(this.member.getMobile());
		um.setQq(this.member.getQq());
		um.setWechat(this.member.getWechat());
		um.setStatus(this.member.getStatus());
		this.memberService.update(um);
		return "success";
	}

	public List getGroupslist() {
		return this.groupslist;
	}

	public void setGroupslist(List groupslist) {
		this.groupslist = groupslist;
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
}
