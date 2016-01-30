package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Area;
import com.ffyc.server.model.Brand;
import com.ffyc.server.model.District;

public interface AreaMapper
{
  public void save(Area area);
  
  public void update(Area area);
  
  public void delete(@Param("id") String id);
  
  public Area findById(@Param("id") String id);

  public List<Area> findAll();
  
  public List<Area> findByArea(@Param("area") Area area,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByArea(@Param("area") Area area);
  
  public List<District> getDistricts(@Param("id") String id); 
  
  public void insertDistricts(@Param("id") String id,@Param("list") List<District> list);
  
  public void deleteDistricts(@Param("id") String id);

}

