package com.ffyc.server.service;

import com.ffyc.server.model.Permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PermissionService
  extends BaseService
{
  public void save(Permission permission);
  
  public void update(Permission permission);
  
  public void delete(String id);
  
  public Permission findById(String id);
  
  public List<Permission> findByManagerAndCode(String manager, String code);
  
  public List<Permission> findByMgroupAndCode(String mgroup, String code);
  
  public List<Permission> findByPermission(Permission permission);
  
  public List<Permission> findAll();
  
  public List<Permission> findAllRoot();
  
  public List<Permission> findAllChild(String pid);
  
}
