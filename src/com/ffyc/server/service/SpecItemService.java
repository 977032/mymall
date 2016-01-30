package com.ffyc.server.service;

import com.ffyc.server.model.SpecItem;
import java.util.List;

public interface SpecItemService
  extends BaseService
{
  public void save(SpecItem specitem);
  
  public void update(SpecItem specitem);
  
  public void delete(String id);
  
  public SpecItem findById(String id);
  
  public List<SpecItem> findAll();
  
}
