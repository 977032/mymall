package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Group;

public interface GroupMapper
{
  public void save(Group group);
  
  public void update(Group group);
  
  public void delete(@Param("id") String id);
  
  public Group findById(@Param("id") String id);
  
  public List<Group> findAll();
  
}
