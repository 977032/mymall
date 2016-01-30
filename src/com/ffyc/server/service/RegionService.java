package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.Region;

public interface RegionService
  extends BaseService
{
  public void save(Region region);
  
  public void update(Region region);
  
  public void delete(String id);
  
  public Region findById(String id);
  
  public List<Region> findAll();
  
}
