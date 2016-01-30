package com.ffyc.server.service;

import com.ffyc.exception.CustomException;
import com.ffyc.exception.permission.DuplicateMobileException;
import com.ffyc.exception.permission.DuplicateUsernameException;
import com.ffyc.exception.permission.EmailSendFailException;
import com.ffyc.exception.permission.IncorrectPasswordException;
import com.ffyc.exception.permission.IncorrectVerifyCodeException;
import com.ffyc.exception.permission.UserNotExistsException;
import com.ffyc.exception.permission.VerifyCodeExpiredException;
import com.ffyc.server.form.LoginManagerForm;
import com.ffyc.server.form.LoginMemberForm;
import com.ffyc.server.form.RegistForm;
import com.ffyc.server.form.RegistFormStepOne;
import com.ffyc.exception.sms.SMSClientException;
import com.ffyc.server.vo.LoginVO;
import com.ffyc.server.form.ThirdPartRegistForm;

public interface AuthenticationService
{

    /**
     * 获取注册手机验证码
     * 
     * @param mobile 手机号
     * @return true，短信接口调用成功
     * @throws SMSClientException 短信发送出错，抛出该异常
     * @exception DuplicateMobileException 手机号码已存在，抛出该异常
     */
    void getRegistVerifyCode(String mobile) throws SMSClientException, DuplicateMobileException;

    /**
     * 注册
     * 
     * @param mobile 手机号
     * @param form 注册表单
     * @return 用户id
     * @exception DuplicateUsernameException 用户名已存在，抛出该异常
     * @exception DuplicateMobileException 手机号码已存在，抛出该异常
     */
    String regist(RegistForm form) throws DuplicateUsernameException, DuplicateMobileException;
    
    /**
     * 注册第一步 
     * @param registFormStepOne
     * @return
     * @throws DuplicateMobileException
     * @throws DuplicateUsernameException 
     * @throws IncorrectVerifyCodeException 
     * @throws VerifyCodeExpiredException 
     */
    String registStepOne(RegistFormStepOne registFormStepOne) throws DuplicateMobileException, DuplicateUsernameException, VerifyCodeExpiredException, IncorrectVerifyCodeException;

    /**
     * member登录
     * 
     * @param form 登录表单
     * @return 部分用户信息字段
     */
    LoginVO loginMember(LoginMemberForm form) throws UserNotExistsException, IncorrectPasswordException;
    
    /**
     * manager登录
     * 
     * @param form 登录表单
     * @return 部分用户信息字段
     */
    LoginVO loginManager(LoginManagerForm form) throws UserNotExistsException, IncorrectPasswordException;

    /**
     * 发送重置密码邮件
     * 
     * @param id 用户id
     * @return 提示信息
     * @throws EmailSendFailException 邮件发送失败，抛出该异常
     * @throws UserNotExistsException 用户不存在，抛出该异常
     */
    String sendPasswordResetEmail(String id) throws EmailSendFailException, VerifyCodeExpiredException, UserNotExistsException;

    /**
     * 修改用户密码
     * 
     * @param id 用户id
     * @param verifyCode 验证码
     * @param password 新密码
     * @throws VerifyCodeExpiredException 如果验证码不正确或验证码已过期，抛出该异常
     */
    void modifyPassword(String id, String verifyCode, String password) throws VerifyCodeExpiredException;

    /**
     * 获取修改手机的验证码
     * 
     * @param mobile 新手机号
     * @throws SMSClientException 短信发送出错，抛出该异常
     */
    void getMobileModificationVerifyCode(String mobile) throws SMSClientException;

    /**
     * 验证注册时的手机验证码
     * 
     * @param mobile 手机号
     * @param verifyCode 验证码
     * @throws VerifyCodeExpiredException 验证码已过期，抛出该异常
     * @throws IncorrectVerifyCodeException 验证码不正确， 抛出该异常
     */
    void validateVerifyCode(String mobile, String verifyCode) throws VerifyCodeExpiredException, IncorrectVerifyCodeException;

    /**
     * 修改用户手机号
     * 
     * @param id 用户id
     * @param verifyCode 验证码
     * @param mobile 新手机号
     * @throws VerifyCodeExpiredException 如果验证码不正确或验证码已过期，抛出该异常
     */
    void modifyMobile(String id, String verifyCode, String mobile) throws VerifyCodeExpiredException;

    /**
     * 重置密码
     * @param userId
     * @param verifyCode
     * @param mobile
     * @param password
     * @throws IncorrectVerifyCodeException 
     * @throws VerifyCodeExpiredException 
     */
	public void resetPasswordByMobileManager(String userId, String verifyCode, String mobile,
			String password) throws VerifyCodeExpiredException, IncorrectVerifyCodeException;
	
	public void resetPasswordByMobileMember(String userId, String verifyCode, String mobile,
			String password) throws VerifyCodeExpiredException, IncorrectVerifyCodeException;

	public LoginVO loginManager(String userId);
	
	public LoginVO loginMember(String userId);

	
	public String thirdPartRegist(ThirdPartRegistForm thirdPartRegistForm)
			throws DuplicateUsernameException, CustomException;
}
