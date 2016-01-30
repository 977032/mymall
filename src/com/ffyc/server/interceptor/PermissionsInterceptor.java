package com.ffyc.server.interceptor;
import java.util.List;
import org.apache.log4j.Logger;
import com.ffyc.server.action.PermissionsAware;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Permission;
import com.ffyc.server.service.PermissionService;
import com.ffyc.server.utils.Notice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PermissionsInterceptor
  extends AbstractInterceptor
{
  private static final long serialVersionUID = -4627867921460839284L;
  private Logger logger = Logger.getLogger(getClass());
  
  public String intercept(ActionInvocation arg0)
    throws Exception
  {
    Object action = arg0.getAction();
    if ((action instanceof PermissionsAware))
    {
      String funccode = ((PermissionsAware)action).getFunccode();
      String funcaction = ((PermissionsAware)action).getFuncaction();
      PermissionService permissionService = ((PermissionsAware)action).getPermissionService();
      this.logger.debug("功能模块funccode:" + funccode);
      Manager manager = (Manager)ActionContext.getContext().getSession().get("manager");
      this.logger.debug("管理员账号类型:" + manager.getCtype());
      if (manager.getCtype().equals(Manager.CTYPE_SUPERADMIN))
      {
        this.logger.info("管理员账号类型:超级管理员");
        return arg0.invoke();
      }
      List list = permissionService.findByManagerAndCode(manager.getId(),funccode);
      Permission permission = null;
      if ((list != null) && (list.size() > 0))
      {
        permission = (Permission)list.get(0);
        if ((permission.getRead().intValue() == Permission.READ) && (funcaction.equals("read")))
        {
          this.logger.info("可读");
          return arg0.invoke();
        }
        if ((permission.getWrite().intValue() == Permission.WRITE) && (funcaction.equals("write")))
        {
          this.logger.info("可写");
          return arg0.invoke();
        }
        if ((permission.getDelete().intValue() == Permission.DELETE) && (funcaction.equals("delete")))
        {
          this.logger.info("可删");
          return arg0.invoke();
        }
        this.logger.info("权限不够");
        Notice notice = new Notice();
        notice.setStatus(false);
        notice.setCode("permission denied");
        notice.setTitle("提示");
        notice.setBody("权限不够");
        ((PermissionsAware)action).setNotice(notice);
        return "notice";
      }
      this.logger.info("没有该功能的权限设置");
      Notice notice = new Notice();
      notice.setStatus(false);
      notice.setCode("not permissions set");
      notice.setTitle("提示");
      notice.setBody("没有该功能的权限设置，默认拒绝");
      ((PermissionsAware)action).setNotice(notice);
      return "notice";
    }
    this.logger.info("没在实现权限接口，不需要权限控制");
    return arg0.invoke();
  }
}
