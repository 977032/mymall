package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Area;
import com.ffyc.server.model.District;

public interface DistrictMapper
{
  public void save(District district);
  
  public void update(District district);
  
  public void delete(@Param("id") Integer id);
  
  public District findById(@Param("id") Integer id);
  
  public List<District> findAllRoot();
  
  public List<District> findAll();
  
  public List<District> findByDistrict(@Param("district") District district,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByDistrict(@Param("district") District district);
  
  public List<Area> getAreas(@Param("id") Integer id);
  
}
