package com.ffyc.server.action.publicer;

import org.apache.commons.mail.HtmlEmail;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.Notice;
import com.nationsky.pub.Env;
import com.opensymphony.xwork2.ActionContext;

public class PasswordFindAction extends BaseAction {

	private static final long serialVersionUID = 4430601492684416730L;
	private String validateCode;
	private String account;
	private String email;

	public String execute() throws Exception {
		return "success";
	}

	public String find() throws Exception {
		//"fp_VerifyCode"
		String verifyCode = (String) ActionContext.getContext().getSession()
				.get("VerifyCode");
		if ((verifyCode == null) || (!verifyCode.equals(this.validateCode))) {
			this.notice.setStatus(false);
			this.notice.setCode("validateCode error");
			this.notice.setTitle("错误");
			this.notice.setBody("验证码错误");
		}else{
			Member member  = this.memberService.findByAccount(this.account);
			if(member!=null){
				try {
					sendmail(member);
				} catch (Exception e) {
					return "error";
				}
			}else{
				return "error";
			}
		}
		return "success";
	}

	private Notice sendmail(Member member) throws Exception {
		Notice notice = new Notice();
		HtmlEmail email = new HtmlEmail();
		/*
		Setting smtp_server = this.settingService.findByProperty("smtp_server");
		Setting smtp_user = this.settingService.findByProperty("smtp_user");
		Setting smtp_password = this.settingService
				.findByProperty("smtp_password");
		email.setHostName(smtp_server.getValue());
		email.setAuthentication(smtp_user.getValue(), smtp_password.getValue());
		*/
		String smtp_server = Env.getProperty("SMTP_HOST");
		String smtp_user = Env.getProperty("EMAIL_USERNAME");
		String smtp_password = Env.getProperty("EMAIL_PASSWORD");
		
		email.setHostName(smtp_server);
		email.setAuthentication(smtp_user, smtp_password);
		email.addTo(member.getEmail(), member.getName());
		email.setFrom(smtp_user, "Beautyfamily电子商务系统");
		email.setSubject("找回密码");
		email.setHtmlMsg("<html><a href=\"http://localhost/beautyfamily/activate.action?a="
				+ member.getActivation() + "\">点击确认修改邮箱</a></html>");
		email.setTextMsg("您的邮箱不支持HTML邮件，您可以在浏览器中浏览以下网址：http://localhost/beautyfamily/emailupdate.action?a="
				+ member.getActivation() + " ，完成邮箱验证操作。");
		email.send();
		return notice;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
