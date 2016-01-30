package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.Notice;

public class PassWordAction extends BaseAction {
	private static final long serialVersionUID = -9133082580127426804L;
	private Notice notice = new Notice();
	private String password;

	public String execute() throws Exception {
		return "success";
	}

	public String change() throws Exception {
		Member member = this.memberService.findById(getMemberFromSession()
				.getId());
		member.setCpassword(this.password);
		this.memberService.update(member);
		this.notice.setStatus(true);
		this.notice.setCode("PASSWORD_CHANGE_SUCCESS");
		this.notice.setTitle("操作成功");
		this.notice.setBody("操作成功，密码被修改，请再使用新密码登录。");
		return "success";
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
