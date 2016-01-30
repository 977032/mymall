package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.ManagerGroup;

public interface ManagerGroupMapper
{
  public void save(ManagerGroup mgroup);
  
  public void update(ManagerGroup mgroup);
  
  public void delete(@Param("id") String id);
  
  public ManagerGroup findById(@Param("id") String id);
  
  public ManagerGroup findByCode(@Param("code") String code);
  
  public List<ManagerGroup> findAll();
}
