package com.ffyc.server.action;

import com.ffyc.server.service.ManagerService;
import com.ffyc.server.service.PermissionService;
import com.ffyc.server.utils.Notice;

public abstract interface PermissionsAware
{
  public abstract String getFunccode();
  
  public abstract void setFunccode(String paramString);
  
  public abstract String getFuncaction();
  
  public abstract void setFuncaction(String paramString);
  
  public abstract String getFuncname();
  
  public abstract void setFuncname(String paramString);
  
  public abstract Notice getNotice();
  
  public abstract void setNotice(Notice notice);
  
  public abstract ManagerService getManagerService();
  
  public abstract void setManagerService(ManagerService managerService);
  
  public abstract PermissionService getPermissionService();
  
  public abstract void setPermissionService(PermissionService permissionService);
}
