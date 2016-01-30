package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Member;

public class SecurityAction extends BaseAction {

	private static final long serialVersionUID = 5896256873914329770L;
	private Member member;

	public String execute() throws Exception {
		this.member = this.memberService.findById(getMemberFromSession()
				.getId());
		return "success";
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
