package com.ffyc.server.service;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.User;
import com.ffyc.server.vo.LoginVO;
import com.ffyc.server.vo.UserTokenVO;

@Service("sessionManager")
@Scope("singleton")
public class SessionManager {

	@Autowired
	private UserService managerService;
	
	@Autowired
	private UserService memberService;
	
	private HashMap<String,String> tokenMap;
	private HashMap<String,LoginVO> loginMap;
	
	public SessionManager(){
		this.tokenMap = new HashMap<String, String>();
		this.loginMap = new HashMap<String, LoginVO>();
	}
	
	public String userLogined(LoginVO vo){
		String id = vo.getId();
		String lastToken = this.getTokenById(id,getUserService(vo));
		if(!StringUtils.isEmpty(lastToken)){
			this.loginMap.remove(lastToken);
		}
		String newToken = UUID.randomUUID().toString();
		this.loginMap.put(newToken, vo);
		this.tokenMap.put(id, newToken);
		
		UserService userService=getUserService(vo);
		UserTokenVO tokenVO = userService.getUserTokenByUserId(id);
		if(tokenVO==null){
			tokenVO = new UserTokenVO();
			tokenVO.setToken(newToken);
			tokenVO.setUserId(id);
			userService.insertUserToken(tokenVO);
		}else
		{
			userService.updateUserToken(id, newToken);
		}
		return newToken;
	}
	
	public LoginVO getLoginVO(String token){
		LoginVO loginVO = this.loginMap.get(token);
		if(loginVO==null){
			UserTokenVO tokenVO = this.managerService.getUserTokenByToken(token);
			if(tokenVO!=null){
				loginVO = new LoginVO();
				loginVO.setId(tokenVO.getUserId());
				loginVO.setToken(tokenVO.getToken());
				String userId= tokenVO.getUserId();
				Manager manager= (Manager)this.managerService.getUserByUserId(userId);
				if(manager!=null){
					loginVO.setAccount(manager.getAccount());
					loginVO.setMobile(manager.getMobile());
					loginVO.setType(User.MANAGER_TYPE);
					loginVO.setName(manager.getName());
				}else{
					Member member= (Member)this.memberService.getUserByUserId(userId);
					if(member!=null){
						loginVO.setAccount(member.getAccount());
						loginVO.setMobile(member.getMobile());
						loginVO.setType(User.MEMBER_TYPE);
						loginVO.setName(member.getName());
					}
				}
				this.loginMap.put(tokenVO.getToken(), loginVO);
			}
		}
		return loginVO;
	}
	
	private String getTokenById(String id,UserService userService){
		String token = this.tokenMap.get(id);
		if(token==null){
			UserTokenVO tokenVO = userService.getUserTokenByUserId(id);
			if(tokenVO!=null){
				token = tokenVO.getToken();
				this.tokenMap.put(id, token);
			}
		}
		return token;
	}
	
	public void logOut(String token){
		LoginVO loginVO  = this.loginMap.get(token);
		UserService userService=getUserService(loginVO);

		this.loginMap.remove(token);
		if(loginVO!=null){
			this.tokenMap.remove(loginVO.getId());
			userService.deleteUserToken(loginVO.getId());
		}
	}
	
	private UserService getUserService(LoginVO vo){
		UserService userService=managerService;
		if(vo.isMember()){
			userService=memberService;
		}
		return userService;
	}
}
