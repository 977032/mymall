package com.ffyc.server.interceptor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.ffyc.server.model.Manager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;


public class ManagerAuthInterceptor
  extends AbstractInterceptor
{
  private static final long serialVersionUID = 2139640528536784511L;
  private Logger logger = Logger.getLogger(getClass());
  private String name;
  
	public String intercept(ActionInvocation arg0) throws Exception {
		
		Manager manager = (Manager) ActionContext.getContext().getSession()
				.get("manager");
		if ((manager != null) && (manager.getId() != null)
				&& (!manager.getId().equals(""))) {
			this.logger.debug("MemberAuth拦截器：已登录");
			return arg0.invoke();
		}
		this.logger.debug("MemberAuth拦截器：未登录");
		return "notauth";
		
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
