package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Permission;

public interface PermissionMapper
{
  public void save(Permission permission);
  
  public void update(Permission permission);
  
  public void delete(@Param("id") String id);
  
  public Permission findById(@Param("id") String id);
  
  public List<Permission> findByManagerAndCode(@Param("manager") String manager, @Param("code") String code);
  
  public List<Permission> findByMgroupAndCode(@Param("mgroup") String mgroup, @Param("code") String code);
  
  public List<Permission> findByPermission(Permission permission);
  
  public List<Permission> findAll();
  
  public List<Permission> findAllRoot();
  
  public List<Permission> findAllChild(@Param("pid") String pid);

}
