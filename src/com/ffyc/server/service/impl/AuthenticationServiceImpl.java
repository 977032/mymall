package com.ffyc.server.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ffyc.exception.CustomException;
import com.ffyc.exception.permission.DuplicateMobileException;
import com.ffyc.exception.permission.DuplicateUsernameException;
import com.ffyc.exception.permission.EmailSendFailException;
import com.ffyc.exception.permission.IncorrectPasswordException;
import com.ffyc.exception.permission.IncorrectVerifyCodeException;
import com.ffyc.exception.permission.UserNotExistsException;
import com.ffyc.exception.permission.VerifyCodeExpiredException;
import com.ffyc.exception.sms.SMSClientException;
import com.ffyc.server.common.ConfigProperty;
import com.ffyc.server.common.ResponseCode;
import com.ffyc.server.common.utils.EmailUtils;
import com.ffyc.server.common.utils.MD5Utils;
import com.ffyc.server.common.utils.SMSClientUtil;
import com.ffyc.server.common.utils.StringUtil;
import com.ffyc.server.form.LoginManagerForm;
import com.ffyc.server.form.LoginMemberForm;
import com.ffyc.server.form.RegistForm;
import com.ffyc.server.form.RegistFormStepOne;
import com.ffyc.server.form.ThirdPartRegistForm;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.UserMapper;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.User;
import com.ffyc.server.service.AuthenticationService;
import com.ffyc.server.service.ThirdPartService;
import com.ffyc.server.service.VerifyCodeStore;
import com.ffyc.server.vo.LoginVO;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ManagerMapper managerMapper;

	@Autowired
	private MemberMapper memberMapper;

	@Autowired
	private ThirdPartService thirdPartService;

	@Override
	public void getRegistVerifyCode(String mobile) throws SMSClientException,
			DuplicateMobileException {
		Member dc =new Member();
		dc.setMobile(mobile);
		List<Member> list = memberMapper.findByMember(dc, null, null);
		if (list.size() > 0) {
			throw new DuplicateMobileException("该手机已经被注册！");
		}
		// modify
		// 暂时不发送短信
		// VerifyCodeStore.putRegistVerifyCode(mobile,
		// SMSClientUtil.sendSMS(mobile));
		// int verifyCode = (int)((Math.random() * 9 + 1) * 100000);
		VerifyCodeStore.putRegistVerifyCode(mobile, String.valueOf("111111"));

	}

	@Override
	@Transactional
	public String regist(RegistForm form) throws DuplicateUsernameException,
			DuplicateMobileException {
		// VerifyCodeStore.removeRegistVerifyCode(mobile);

		Manager user = new Manager();
		user.setName(form.getUsername());
		user.setPassword(MD5Utils.getMD5Str(form.getPassword()));
		user.setMobile(form.getMobile());
		user.setGender(form.getGender());
		String mobile = form.getMobile();

		Manager dc = new Manager();
		dc.setName(user.getName());
		List<Manager> list = managerMapper.findByManager(dc, null, null);
		if(list.size()>0){
			throw new DuplicateUsernameException("该昵称已被使用！");
		}
		/*
		 * if(userMapper.isMobileExists(mobile) > 0) { throw new
		 * DuplicateMobileException("该手机已经被注册！"); }
		 */

		managerMapper.save(user);

		return user.getId();
	}

	@Override
	public String registStepOne(RegistFormStepOne registFormStepOne)
			throws DuplicateMobileException, DuplicateUsernameException,
			VerifyCodeExpiredException, IncorrectVerifyCodeException {
		this.validateVerifyCode(registFormStepOne.getMobile(),
				registFormStepOne.getVerifyCode());
		// TODO Auto-generated method stub
		Member member = new Member();
		member.setPassword(MD5Utils.getMD5Str(registFormStepOne.getPassword()));
		member.setMobile(registFormStepOne.getMobile());
		member.setName(registFormStepOne.getName());
		member.setGender(registFormStepOne.getGender());
		member.setId(StringUtil.getUUID());
		member.setAccount(registFormStepOne.getMobile());
		String mobile = registFormStepOne.getMobile();

		Member dc= new Member();
		dc.setMobile(mobile);
		List<Member> list =memberMapper.findByMember(dc, null, null);
		if (list.size() > 0) {
			throw new DuplicateMobileException("该手机已经被注册。");
		}
		memberMapper.save(member);

		return member.getId();
	}

	@Override
	@Transactional
	public String thirdPartRegist(ThirdPartRegistForm thirdPartRegistForm)
			throws CustomException {
		String platForm = thirdPartRegistForm.getPlatform();
		String pId = thirdPartRegistForm.getpId();
		String userId = this.thirdPartService.getUserId(platForm, pId);
		if (!org.apache.commons.lang.StringUtils.isEmpty(userId))
			throw new CustomException(ResponseCode.DUPLICATE_PLATFORM_ID,
					"平台账号已被注册。");

		String name = thirdPartRegistForm.getName();
		name = platForm + "_" + name;
		String tmp = "" + new Date().getTime();
		tmp = tmp.substring(tmp.length() - 3);
		name = name + tmp;
		Member user = new Member();
		user.setName(name);
		user.setId(StringUtil.getUUID());
		user.setAccount(name);
		user.setPassword(MD5Utils.getMD5Str(Member.DEFAULT_PASSWORD));

		Member dc = new Member();
		dc.setName(user.getName());
		List<Member> list = memberMapper.findByMember(dc, null, null);
		if (list.size() > 0) {
			throw new DuplicateUsernameException("该名称已被使用！");
		}

		user.setGender(thirdPartRegistForm.getGender());

		memberMapper.save(user);

		userId = user.getId();

		this.thirdPartService.insertThirdPartUser(userId, platForm, pId);

		return user.getId();
	}

	@Override
	public LoginVO loginMember(LoginMemberForm form)
			throws UserNotExistsException, IncorrectPasswordException {
		String loginID = form.getAccount();
		LoginVO vo = null;
		if (memberMapper.isExists(form.getAccount(),
				MD5Utils.getMD5Str(form.getPassword())) == 0) {
			throw new IncorrectPasswordException("手机号或密码错误");
		}
		Member dc = new Member();
		dc.setStatus(Member.STATUS_NORMAL);
		dc.setAccount(loginID);
		List<Member> list = memberMapper.findByMember(dc, null, null);
		if (list.size() > 0) {
			Member member = list.get(0);
			vo = new LoginVO();
			vo.setId(member.getId());
			vo.setAccount(member.getAccount());
			vo.setName(member.getName());
			vo.setMobile(member.getMobile());
			vo.setType(Member.MEMBER_TYPE);
		}
		return vo;
	}

	@Override
	public LoginVO loginManager(LoginManagerForm form)
			throws UserNotExistsException, IncorrectPasswordException {
		String loginID = form.getAccount();
		LoginVO vo = null;
		if (managerMapper.isExists(loginID,
				MD5Utils.getMD5Str(form.getPassword())) == 0) {
			throw new IncorrectPasswordException("工号或密码错误");
		}
		Manager dc = new Manager();
		dc.setAccount(loginID);
		dc.setStatus(Manager.STATUS_NORMAL);
		List<Manager> list = managerMapper.findByManager(dc, null, null);
		if(list.size()>0){
			Manager manager = list.get(0);
			vo = new LoginVO();
			vo.setId(manager.getId());
			vo.setName(manager.getName());
			vo.setAccount(manager.getAccount());
			vo.setMobile(manager.getMobile());
			vo.setType(Manager.MANAGER_TYPE);
		}
		return vo;
	}

	@Override
	public LoginVO loginManager(String userId) {
		User u = managerMapper.findById(userId);
		if (u == null)
			return null;
		LoginVO v = new LoginVO();
		v.setId(userId);
		v.setName(u.getName());
		return v;
	}

	@Override
	public LoginVO loginMember(String userId) {
		Member u = (Member) memberMapper.findById(userId);
		if (u == null)
			return null;
		LoginVO v = new LoginVO();
		v.setId(userId);
		v.setName(u.getName());
		v.setAccount(u.getAccount());
		v.setMobile(u.getMobile());
		v.setType(User.MEMBER_TYPE);
		return v;
	}

	@Override
	public void resetPasswordByMobileManager(String userId, String verifyCode,
			String mobile, String password) throws VerifyCodeExpiredException,
			IncorrectVerifyCodeException {
		this.validateVerifyCode(mobile, verifyCode);
		managerMapper.updatePassword(userId, MD5Utils.getMD5Str(password));
	}

	@Override
	public void resetPasswordByMobileMember(String userId, String verifyCode,
			String mobile, String password) throws VerifyCodeExpiredException,
			IncorrectVerifyCodeException {
		this.validateVerifyCode(mobile, verifyCode);
		memberMapper.updatePassword(userId, MD5Utils.getMD5Str(password));
	}

	@Override
	public String sendPasswordResetEmail(String name)
			throws UserNotExistsException, EmailSendFailException,
			VerifyCodeExpiredException {
		LoginVO user= null;
		Manager dc = new Manager();
		dc.setName(name);
		List<Manager> list = managerMapper.findByManager(dc, null, null);
		if(list.size()>0){
			Manager manager =list.get(0);
			user = new LoginVO();
			user.setId(manager.getId());
			user.setName(manager.getName());
			user.setMobile(manager.getMobile());
			user.setAccount(manager.getAccount());
			user.setType(Manager.MANAGER_TYPE);
		}
		String verifyCode = UUID.randomUUID().toString();

		if (user == null) {
			throw new UserNotExistsException("用户不存在");
		}

		VerifyCodeStore.putPasswordResetVerifyCode(user.getId(), verifyCode);

		long expireHour = ConfigProperty.PASSWORD_RESET_VERIFY_CODE_EXPIRE_MILLIS / 1000 / 60 / 60;
		String link = ConfigProperty.PASSWORD_RESET_URL_PREFIX + verifyCode
				+ "?userId=" + user.getId();

		String subject = "同行网密码重置邮件";
		String content = "<html><body><h3>请尽快打开这个链接重置密码，该链接将在 " + expireHour
				+ " 小时后失效 </h3><p><a href='" + link + "'>" + link
				+ "</a></p></body><html>";
		try {
			EmailUtils.sendTextEmail(
					ConfigProperty.PASSWORD_RESET_EMAIL_SENDER,
					ConfigProperty.EMAIL_USERNAME,
					ConfigProperty.EMAIL_PASSWORD, name, subject, content);
		} catch (MessagingException e) {
			throw new EmailSendFailException("抱歉，重置密码邮件发送失败");
		}

		return "邮件发已发送到" + name + ", 请在" + expireHour + "小时内查收并重置密码";
	}

	@Override
	public void modifyPassword(String id, String verifyCode, String password)
			throws VerifyCodeExpiredException {
		if (!verifyCode.equals(VerifyCodeStore.getPasswordResetVerifyCode(id))) {
			throw new VerifyCodeExpiredException("您的重置密码邮件可能已过期，请重新发起重置密码请求");
		}

		VerifyCodeStore.removePasswordResetVerifyCode(id);

		managerMapper.updatePassword(id, MD5Utils.getMD5Str(password));
	}

	@Override
	public void getMobileModificationVerifyCode(String mobile)
			throws SMSClientException {
		VerifyCodeStore.putMobileModificationVerifyCode(mobile,
				SMSClientUtil.sendSMS(mobile));
	}

	@Override
	public void modifyMobile(String id, String verifyCode, String mobile)
			throws VerifyCodeExpiredException {
		if (!verifyCode.equals(VerifyCodeStore
				.getMobileModificationVerifyCode(mobile))) {
			throw new VerifyCodeExpiredException("手机验证码不正确");
		}

		VerifyCodeStore.removeMobileModificationVerifyCode(mobile);
		Manager manager =managerMapper.findById(id);
		manager.setMobile(mobile);
		managerMapper.update(manager);
	}

	@Override
	public void validateVerifyCode(String mobile, String verifyCode)
			throws VerifyCodeExpiredException, IncorrectVerifyCodeException {
		// modify
		// 暂时采用固定VerifyCode
		if ("111111".equals(verifyCode)) {
			return;
		}
		if (!StringUtil.isEqual(VerifyCodeStore.getRegistVerifyCode(mobile),
				verifyCode)) {
			throw new IncorrectVerifyCodeException("手机验证码不正确");
		}
		if (!"111111".equals(verifyCode))
			throw new IncorrectVerifyCodeException("手机验证码不正确");
	}

}
