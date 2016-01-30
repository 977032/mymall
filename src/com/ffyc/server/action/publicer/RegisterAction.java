package com.ffyc.server.action.publicer;

import org.apache.commons.mail.HtmlEmail;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Config;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.PaySetting;
import com.ffyc.server.utils.Notice;

public class RegisterAction extends BaseAction {
	private static final long serialVersionUID = 5291242583321987938L;
	private Notice notice = new Notice();
	private Member member = new Member();
	private String validateCode;

	public String execute() throws Exception {
		GlobalChannelContent(this.map);
		return "success";
	}

	public String registering() throws Exception {
		Config funcRegister = this.configService.findByProperty("funcRegister");
		if ((funcRegister != null) && (funcRegister.getValue() != null)
				&& (funcRegister.getValue().equals("enable"))) {
			this.notice.setStatus(false);
			this.notice.setCode("Register_Disabled");
			this.notice.setTitle("提示：");
			this.notice.setBody("系统关闭了新用户注册，请稍候再试，或联系管理员。");
			return "error";
		}
		this.notice = this.memberService.register(this.member,
				this.validateCode);
		return "success";
	}

	private Notice sendmail(Member member) throws Exception {
		Notice notice = new Notice();
		HtmlEmail email = new HtmlEmail();
		PaySetting smtp_server = this.paySettingService.findByProperty("smtp_server");
		PaySetting smtp_user = this.paySettingService.findByProperty("smtp_user");
		PaySetting smtp_password = this.paySettingService
				.findByProperty("smtp_password");
		email.setHostName(smtp_server.getValue());
		email.setAuthentication(smtp_user.getValue(), smtp_password.getValue());
		email.addTo(member.getEmail(), member.getName());
		email.setFrom(smtp_user.getValue(), "beautyfamily电子商务系统");
		email.setSubject("请激活您的账户");
		email.setHtmlMsg("<html><a href=\"http://localhost:8080/beautyfamily/activate.action?a="
				+ member.getActivation() + "\">点击确认修改邮箱</a></html>");
		email.setTextMsg("您的邮箱不支持HTML邮件，您可以在浏览器中浏览以下网址：http://localhost:8080/beautyfamily/emailupdate.action?a="
				+ member.getActivation() + " ，完成邮箱验证操作。");
		email.send();
		return notice;
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

	public String getValidateCode() {
		return this.validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
}
