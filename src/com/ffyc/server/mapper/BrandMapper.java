package com.ffyc.server.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Brand;

public interface BrandMapper
{
  public void save(Brand brand);
  
  public void update(Brand brand);
  
  public void delete(@Param("id") String id);
  
  public Brand findById(@Param("id") String id);
  
  public List<Brand> findByBrand(@Param("brand") Brand brand,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByBrand(@Param("brand") Brand brand);
  
  public List<Brand> findAll();
  
}

