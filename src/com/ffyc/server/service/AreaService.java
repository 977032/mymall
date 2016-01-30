package com.ffyc.server.service;

import com.ffyc.server.model.Area;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;

public interface AreaService
  extends BaseService
{
  public void save(Area area);
  
  public void update(Area area);
  
  public void delete(String id);
  
  public Area findById(String id);
  
  public List<Area> findAll();
  
  public List<Area> findByArea(Area area,Integer startIndex,Integer pageSize); 
  
  public int getCountByArea(Area area);
  
  public PaginationSupport findPageByArea(Area area,Integer page,Integer pageSize);
}

