package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Bank;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.Point;
import com.ffyc.server.utils.Notice;

public class BankAction extends BaseAction {
	private static final long serialVersionUID = 7479188563507438763L;
	private Notice notice = new Notice();
	private Member member;

	public String execute() throws Exception {
		this.member = this.memberService.findById(getMemberFromSession()
				.getId());
		return "success";
	}

	public String openaccount() throws Exception {
		this.member = this.memberService.findById(getMemberFromSession()
				.getId());
		if (this.member.getBank() != null) {
			this.notice.setStatus(false);
			this.notice.setCode("ACCOUNT_EXIST");
			this.notice.setTitle("已有资金账户");
			this.notice.setBody("已有资金账户，不能开新资金账户。");
			return "error";
		}
		Bank bank = this.bankService.openAccount();
		this.member.setBankk(bank);
		Point point = this.pointService.openAccount();
		this.member.setPointt(point);
		this.memberService.update(this.member);
		return "success";
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
