package com.ffyc.server.action.manager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.ffyc.server.action.PermissionsAware;
import com.ffyc.server.action.SuperAction;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Member;
import com.ffyc.server.service.PermissionService;
import com.ffyc.server.utils.Notice;
import com.opensymphony.xwork2.ActionContext;

public class BaseAction extends SuperAction implements ServletRequestAware,
		ServletResponseAware, SessionAware, PermissionsAware {
	private static final long serialVersionUID = 1766809084625508348L;
	protected Logger logger = Logger.getLogger(getClass());


	public String getFunccode() {
		return this.funccode;
	}

	public void setFunccode(String funccode) {
		this.funccode = funccode;
	}

	public String getFuncaction() {
		return this.funcaction;
	}

	public void setFuncaction(String funcaction) {
		this.funcaction = funcaction;
	}

	public String getFuncname() {
		return this.funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
	}

	@Override
	public Notice getNotice() {
		return notice;
	}

	@Override
	public void setNotice(Notice notice) {
		this.notice= notice;
	}

	@Override
	public PermissionService getPermissionService() {
		return this.permissionService;
	}

	@Override
	public void setPermissionService(PermissionService permissionService) {
		this.permissionService = permissionService;
	}

}
