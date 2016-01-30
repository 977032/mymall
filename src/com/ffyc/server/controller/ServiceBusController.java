package com.ffyc.server.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ffyc.exception.CustomException;
import com.ffyc.exception.attach.AttachIOException;
import com.ffyc.exception.permission.IncorrectVerifyCodeException;
import com.ffyc.exception.permission.VerifyCodeExpiredException;
import com.ffyc.server.common.FailureResponseMessage;
import com.ffyc.server.common.ResponseCode;
import com.ffyc.server.common.ResponseMessage;
import com.ffyc.server.common.SuccessResponseMessage;
import com.ffyc.server.common.utils.StringUtil;
import com.ffyc.server.form.LoginManagerForm;
import com.ffyc.server.form.LoginMemberForm;
import com.ffyc.server.form.PasswordResetForm;
import com.ffyc.server.form.RegistForm;
import com.ffyc.server.form.RegistFormStepOne;
import com.ffyc.server.form.ThirdPartLoginForm;
import com.ffyc.server.form.ThirdPartRegistForm;
import com.ffyc.server.form.UserBasicInfoFormModify;
import com.ffyc.server.form.UserLocationForm;
import com.ffyc.server.model.PushInfo;
import com.ffyc.server.model.UserBasicInfo;
import com.ffyc.server.model.UserMessageForm;
import com.ffyc.server.service.AppService;
import com.ffyc.server.service.AppversionService;
import com.ffyc.server.service.AuthenticationService;
import com.ffyc.server.service.MessageService;
import com.ffyc.server.service.SessionManager;
import com.ffyc.server.service.ThirdPartService;
import com.ffyc.server.service.UserService;
import com.ffyc.server.vo.LoginVO;
import com.ffyc.server.vo.MessageVO;

@Controller
@RequestMapping
public class ServiceBusController
{

    @Autowired
    private AuthenticationService authenticationService;
    
    @Autowired
    private UserService managerService;
    
    @Autowired
    private UserService memberService;

    @Autowired
    private AppversionService appversionService;
    
    
    @Autowired
    private AppService appService;
    
    
    @Autowired
    private SessionManager sessionManager;
    
    
    @Autowired
    private MessageService messageService;
    
    @Autowired
    private ThirdPartService thirdPartService;
    
    @RequestMapping("/doc")
    public String doc()
    {
        return "/doc";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login()
    {
        return "/login";
    }
    
    /**
     * member用户登录
     */
    @RequestMapping(value = "/LoginMember", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage loginMember(@RequestBody LoginMemberForm form)
    {
        try
        {
        	LoginVO vo = authenticationService.loginMember(form);
        	String token = this.sessionManager.userLogined(vo);
            vo.setToken(token);
            return new SuccessResponseMessage(vo);
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
    }
    /**
     * manager用户登录
     */
    @RequestMapping(value = "/LoginManager", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage loginManager(@RequestBody LoginManagerForm form)
    {
        try
        {
        	LoginVO vo = authenticationService.loginManager(form);
        	String token = this.sessionManager.userLogined(vo);
            vo.setToken(token);
            return new SuccessResponseMessage(vo);
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
    }
    
    /**
     * 三方登录
     */
    @RequestMapping(value = "/ThirdPartLogin", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage thirdPartLogin(@RequestBody ThirdPartLoginForm form)
    {
        	String userId = this.thirdPartService.getUserId(form.getPlatform(), form.getpId());
        	LoginVO vo = authenticationService.loginMember(userId);
        	if(vo==null){
        		vo = new LoginVO();
        	}else{
        		String token = this.sessionManager.userLogined(vo);
        		vo.setToken(token);
        	}
            return new SuccessResponseMessage(vo);
        
    }
    
    /**
     *退出登录
     */
    @RequestMapping(value = "/{token}/Logout", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage logout(@PathVariable(value="token") String token )
    {
        LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			this.sessionManager.logOut(token);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new SuccessResponseMessage();
    }

    /**
     * 用户注册
     */
    @RequestMapping(value = "/Regist", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage regist(@RequestBody RegistForm form)
    {
        try
        {
            authenticationService.regist(form);
        }
        catch(CustomException e) 
        {
            return new FailureResponseMessage(e);
        }
        return new SuccessResponseMessage();
    }
    
    /**
     * 获取用户注册短信验证码
     */
    @RequestMapping(value = "/Regist/{mobile}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage getRegistVerifyCode(@PathVariable String mobile)
    {
        try
        {
            authenticationService.getRegistVerifyCode(mobile);
            return new SuccessResponseMessage();
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
    }
    
    /**
     * 获取重置密码短信验证码
     */
    @RequestMapping(value = "/Password/{mobile}", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage getResetPasswordVerifyCode(@PathVariable String mobile)
    {
        try
        {
            authenticationService.getRegistVerifyCode(mobile);
            return new SuccessResponseMessage();
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
    }
    
    /**
     * member注册步骤一
     */
    @RequestMapping(value = "/MemberRegistStepOne", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage registStepOne(@RequestBody RegistFormStepOne registFormStepOne)
    {
    	LoginVO loginVO = null;
        try
        {
            authenticationService.registStepOne(registFormStepOne);
            LoginMemberForm form = new LoginMemberForm();
            form.setPassword(registFormStepOne.getPassword());
            form.setAccount(registFormStepOne.getMobile());
        	loginVO = authenticationService.loginMember(form);
        	String token = this.sessionManager.userLogined(loginVO);
        	loginVO.setToken(token);
            
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
        Map<String,String> result = new HashMap<String,String>();
        result.put("token", loginVO.getToken());
        result.put("id", loginVO.getId());
        result.put("name", loginVO.getName());
        return new SuccessResponseMessage(result);
    }
    
    /**
     * 三方平台注册
     */
    @RequestMapping(value = "/ThirdPartRegist", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage registStepOne(@RequestBody ThirdPartRegistForm thirdPartRegistForm)
    {
    	LoginVO loginVO = null;
        try
        {
            String userId = authenticationService.thirdPartRegist(thirdPartRegistForm);
            loginVO = this.authenticationService.loginMember(userId);
            String token = this.sessionManager.userLogined(loginVO);
            loginVO.setToken(token);
            
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
        Map<String,String> result = new HashMap<String,String>();
        result.put("token", loginVO.getToken());
        result.put("id", loginVO.getId());
        result.put("name", loginVO.getName());
        return new SuccessResponseMessage(result);
    }
    
    /**
     * 用户基本信息修改
     */
    @RequestMapping(value = "/UserBasicInfo/{token}", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage userBasicInfo(@PathVariable(value="token") String token,@RequestBody UserBasicInfoFormModify basicInfoForm)
    {
    	String userId = "";
    	try
        {
        	LoginVO vo = this.getLoginVO(token);
        	userId = vo.getId();
        	UserService userService = getUserService(vo);
        	userService.updateUserBasicInfo(userId, basicInfoForm.getGender(), basicInfoForm.getName());
        	vo.setName(basicInfoForm.getName());
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
        Map<String,String> result = new HashMap<String,String>();
        result.put("id", userId);
        return new SuccessResponseMessage(result);
    }
    
    /**
     * 密码重置-手机
     */
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage resetPasswordByMobile(@RequestBody PasswordResetForm passwordResetForm)
    {
    		String userId = "";
    		LoginVO vo = this.memberService.getUserByMobile(passwordResetForm.getMobile());
    		if(vo==null){
    			CustomException ce = new CustomException("0x00", "用户不存在");
    			return new FailureResponseMessage(ce);
    		}
        	userId = vo.getId(); 
        	try {
        		if(vo.isManager()){
    				this.authenticationService.resetPasswordByMobileManager(userId, passwordResetForm.getVerifyCode(), passwordResetForm.getMobile(), passwordResetForm.getPassword());
        		}else if(vo.isMember()){
				    this.authenticationService.resetPasswordByMobileMember(userId, passwordResetForm.getVerifyCode(), passwordResetForm.getMobile(), passwordResetForm.getPassword());
        		}
			} catch (IncorrectVerifyCodeException e) {
				return new FailureResponseMessage(e);
			} catch (VerifyCodeExpiredException e) {
				e.printStackTrace();
			}
        return new SuccessResponseMessage();
    }

    
    
   
    
    /**
     * 按名称搜索Member用户
     */
    @RequestMapping(value = "/{token}/searchMemberByName", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage searchMemberByName(@PathVariable(value="token") String token,@RequestParam String name,@RequestParam int page)
    {
    	String userId = "";
    	try {
    		LoginVO vo = this.getLoginVO(token);
			name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			userId = vo.getId();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}

    	List<UserBasicInfo> result =  this.memberService.searchUserByName(name, page);
    	return new SuccessResponseMessage(result);
    }
    
    /**
     * 按名称搜索Manager用户
     */
    @RequestMapping(value = "/{token}/searchManagerByName", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage searchManagerByName(@PathVariable(value="token") String token,@RequestParam String name,@RequestParam int page)
    {
    	String userId = "";
    	try {
    		LoginVO vo = this.getLoginVO(token);
			name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
			userId = vo.getId();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}

    	List<UserBasicInfo> result =  this.managerService.searchUserByName(name, page);
    	return new SuccessResponseMessage(result);
    }
    
    /**
     * 下载头像
     * 
     * @throws IOException
     */
    @RequestMapping(value = "/User/Info/{id}/Photo", method = RequestMethod.GET)
    public void downloadUserPhoto(@PathVariable String id, HttpServletResponse response) throws IOException
    {
        try
        {
            memberService.downloadPhoto(id, response, response.getOutputStream());
        }
        catch(FileNotFoundException e)
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        }
        catch(AttachIOException e)
        {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    /**
     * 修改头像
     */
    @RequestMapping(value = "/User/Info/{token}/Photo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage uploadPhoto(@PathVariable String token, @RequestParam("attach") MultipartFile attach)
    {
        try
        {
        	LoginVO vo = this.getLoginVO(token);
        	UserService userService = getUserService(vo);
            userService.modifyUserPhoto(vo.getId(), attach);
            return new SuccessResponseMessage();
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
    }
    
    /**
     * 用户地理位置设置
     */
    @RequestMapping(value = "/{token}/Location", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseMessage updateUserLocation(@PathVariable(value="token") String token,@RequestBody UserLocationForm userLocationForm)
    {
    	String userId = "";
    	try
        {
        	LoginVO vo = this.getLoginVO(token);
        	userId = vo.getId();
        	UserService userService = getUserService(vo);
        	userService.updateUserLocation(userId, userLocationForm.getLng(), userLocationForm.getLat());
        }
        catch(CustomException e)
        {
            return new FailureResponseMessage(e);
        }
        return new SuccessResponseMessage();
    }
    
    
    
    /**
     * 获取用户协议
     */
    
    @RequestMapping(value = "/UserContract", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getUserContract()
    {
    	String contract = this.appService.getUserContract();
    	Map<String,String> result = new HashMap<String,String>();
        result.put("userContract", contract);
        return new SuccessResponseMessage(result);
    }
  
    
    /**
     * 获取最新App版本
     */
    @RequestMapping(value = "/Appversion/{appType}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage checkAppversion(@PathVariable String appType)
    {
           return new SuccessResponseMessage(appversionService.getLatestVersion(appType));
    }
    
    /**
     * 获取系统消息列表
     */
    @RequestMapping(value = "/{token}/SystemMsg", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getSystemMsgList(@PathVariable String token,@RequestParam(required=false) int page)
    {
    	LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			String userId = vo.getId();
			return new SuccessResponseMessage(this.messageService.getSystemMessageList(userId, page));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}
    }
    
    /**
     * 删除系统消息
     */
    @RequestMapping(value = "/{token}/SystemMsg/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseMessage deleteSystemMsg(@PathVariable String token,@PathVariable String id/*,@RequestParam(required=false) int page*/)
    {
    	LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			String userId = vo.getId();
			this.messageService.deleteSystemMessage(userId, id);
			return new SuccessResponseMessage();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}
    }
    
    /**
     * 发送消息
     */
    @RequestMapping(value = "/{token}/Message", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage sendUserMessage(@PathVariable String token, @RequestBody UserMessageForm userMessageForm)
    {
    	LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			String userId = vo.getId();
			MessageVO um = new MessageVO();
			um.setId(StringUtil.getUUID());
			um.setContent(userMessageForm.getContent());
			um.setFromId(userId);
			um.setToId(userMessageForm.getToId());
			um.setFromName(vo.getName());
			String id = this.messageService.insertUserMessage(um);
			HashMap<String,String> result = new HashMap<String, String>();
			result.put("id", id);
			return new SuccessResponseMessage(result);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}
    }
    
    /**
     * 获取聊天记录
     */
    @RequestMapping(value = "/{token}/Message/User/{with}", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getUserMsgList(@PathVariable String token,@PathVariable String with,@RequestParam int page)
    {
    	LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			String userId = vo.getId();
			return new SuccessResponseMessage(this.messageService.getUserMessageList(with,userId , page));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}
    }
    
    /**
     * 获取消息列表
     */
    @RequestMapping(value = "/{token}/Message", method = RequestMethod.GET)
    public @ResponseBody
    ResponseMessage getMsgList(@PathVariable String token/*,@RequestParam(required=false) int page*/)
    {
    	LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			String userId = vo.getId();
			return new SuccessResponseMessage(this.messageService.getMessageList(userId, 0));
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}
    }
    
    /**
     * 删除聊天信息
     */
    @RequestMapping(value = "/{token}/Message/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseMessage deleteUserMessage(@PathVariable String token,@PathVariable String id)
    {
    	LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			String userId = vo.getId();
			this.messageService.deleteUserMessage(userId, id);
			return new SuccessResponseMessage();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}
    }
    
    /**
     * 删除对话
     */
    @RequestMapping(value = "/{token}/Chat/{friendId}", method = RequestMethod.DELETE)
    public @ResponseBody
    ResponseMessage deleteUserChat(@PathVariable String token,@PathVariable String friendId)
    {
    	LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			String userId = vo.getId();
			this.messageService.deleteUserFriendMessage(userId, friendId);
			return new SuccessResponseMessage();
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			return new FailureResponseMessage(e);
		}
    }
    
    /**
     * 设置推送平台信息
     */
    @RequestMapping(value = "/{token}/PushInfo", method = RequestMethod.POST)
    public @ResponseBody
    ResponseMessage setPushUserId(@PathVariable String token, @RequestBody PushInfo pushInfo)
    {
    	LoginVO vo;
		try {
			vo = this.getLoginVO(token);
			String userId = vo.getId();
			String pushUserId = pushInfo.getPushUserId();
			this.messageService.setPushUserId(userId, pushUserId);
			return new SuccessResponseMessage();
		} catch (CustomException e) {
			return new FailureResponseMessage(e);
		}
    }
    
    private LoginVO getLoginVO(String token) throws CustomException{
    	if(org.apache.commons.lang.StringUtils.isEmpty(token))
    		throw new CustomException(ResponseCode.TOKEN_EXPIRED,"登录超时");
    	LoginVO vo = this.sessionManager.getLoginVO(token); 
    	if(vo==null)
    		throw new CustomException(ResponseCode.TOKEN_EXPIRED,"登录超时");
    	return vo;
    }
    private UserService getUserService(LoginVO vo){
    	UserService userService = this.managerService;
    	if(vo.isMember()){
    		userService = this.memberService;
    	}
    	return userService;
    }

}
