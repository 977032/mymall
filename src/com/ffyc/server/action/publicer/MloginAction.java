package com.ffyc.server.action.publicer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.ffyc.server.action.BaseAction;
import com.ffyc.server.utils.Notice;

public class MloginAction extends BaseAction {
	
	private static final long serialVersionUID = -3821538859334280135L;
	static final Logger logger = Logger.getLogger(IndexAction.class.getName());
	private String url = "";
	private Notice notice;
	private String account;
	private String password;
	private String validateCode;

	public String execute() throws Exception {
		this.url = this.request.getHeader("referer");
		logger.info("待转向URL:" + this.url);
		return "success";
	}

	public String loggingin() throws Exception {
		if ((this.url == null) || (!StringUtils.isNotEmpty(this.url))
				|| (this.url.endsWith("/mlogin.htm"))) {
			this.url = "/manager/index.htm";
		}
		this.notice = this.managerService.login(this.account, this.password,
				this.validateCode);
		if (this.notice.isStatus()) {
			logger.info("账号 " + this.account + " 成功登录。");
			return "success";
		}
		logger.warn("账户 " + this.account + " 尝试登录，登录失败。");
		return "error";
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String logout() throws Exception {
		this.session.clear();
		return "success";
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getValidateCode() {
		return this.validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
}
