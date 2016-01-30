package com.ffyc.server.action.manager;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.common.utils.MD5Utils;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.Notice;

public class MemberPasswordAction extends BaseAction {
	private static final long serialVersionUID = 2803783894393820588L;
	private String mbid;
	private Member member;
	private String password;
	private Notice notice = new Notice();

	public String execute() throws Exception {
		this.member = this.memberService.findById(this.mbid);
		return "success";
	}

	public String update() throws Exception {
		Member um = this.memberService.findById(this.mbid);
		if (StringUtils.isNotEmpty(this.password)) {
			um.setCpassword(this.password);
			um.setPassword(MD5Utils.getMD5Str(this.password));
			try {
				this.memberService.update(um);
				this.notice.setStatus(true);
				this.notice.setCode("success");
				this.notice.setTitle("密码设置成功");
				this.notice.setBody("密码设置成功。");
			} catch (Exception e) {
				this.notice.setStatus(false);
				this.notice.setCode("member_password_error");
				this.notice.setTitle("密码修改错误");
				this.notice.setBody(String.valueOf(e));
			}

		} else {
			this.notice.setStatus(false);
			this.notice.setCode("member_password_null");
			this.notice.setTitle("密码不允许为空");
			this.notice.setBody("基于安全原因，不允许设置密码为空，请填写合适的密码。");
		}
		return "notice";
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}
}
