package com.ffyc.server.action.publicer;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Config;
import com.ffyc.server.utils.Notice;

public class LoginAction extends BaseAction {
	private static final long serialVersionUID = -8080971851916350575L;
	private static final Logger logger = Logger.getLogger(LoginAction.class);
	private String url;
	private Notice notice = new Notice();
	private String account;
	private String password;
	private String validateCode;

	public String execute() throws Exception {
		GlobalChannelContent(this.map);
		this.url = ((String) this.session.get("url"));
		logger.info("待转向URL:" + this.url);
		return "success";
	}

	public String loggingin() throws Exception {
		Config funcLogin = this.configService.findByProperty("funcLogin");
		if ((funcLogin != null) && (funcLogin.getValue() != null)
				&& (funcLogin.getValue().equals("enable"))) {
			this.notice.setStatus(false);
			this.notice.setCode("Login_Disabled");
			this.notice.setTitle("提示：");
			this.notice.setBody("系统关闭了用户登录，请稍候再试，或联系管理员。");
			return "error";
		}
		this.url = ((String) this.session.get("url"));
		if (StringUtils.isNotEmpty(this.url)) {
			this.url.equals("my_cart.htm");
		} else {
			this.url = "/member/index.htm";
		}
		this.notice = this.memberService.login(this.account, this.password,
				this.validateCode);
		if (this.notice.isStatus()) {
			return "success";
		}
		return "error";
	}

	public String logout() throws Exception {
		this.session.clear();
		return "success";
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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
