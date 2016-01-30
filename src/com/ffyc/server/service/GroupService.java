package com.ffyc.server.service;

import com.ffyc.server.model.Group;
import java.util.List;

public interface GroupService
  extends BaseService
{
  public void save(Group group);
  
  public void update(Group group);
  
  public void delete(String id);
  
  public Group findById(String id);
  
  public List<Group> findAll();
  
}
