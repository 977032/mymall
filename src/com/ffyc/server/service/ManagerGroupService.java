package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.ManagerGroup;

public interface ManagerGroupService
  extends BaseService
{
  public void save(ManagerGroup mgroup);
  
  public void update(ManagerGroup mgroup);
  
  public void delete(String id);
  
  public ManagerGroup findById(String id);
  
  public ManagerGroup findByCode(String code);
  
  public List<ManagerGroup> findAll();
  
}
