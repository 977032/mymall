package com.ffyc.server.interceptor;

import org.apache.log4j.Logger;

import com.ffyc.server.model.Member;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class MemberAuthInterceptor
  extends AbstractInterceptor
{
  private static final long serialVersionUID = 7086773816999065732L;
  private Logger logger = Logger.getLogger(getClass());
  private String name;
  
  public String intercept(ActionInvocation arg0)
    throws Exception
  {
    Member member = (Member)ActionContext.getContext().getSession().get("member");
    if ((member != null) && (member.getId() != null) && (!member.getId().equals("")))
    {
      this.logger.debug("MemberAuth拦截器：已登录");
      return arg0.invoke();
    }
    this.logger.debug("MemberAuth拦截器：未登录");
    return "notauth";
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
}
