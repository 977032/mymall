package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.GoodType;
import com.ffyc.server.model.SpecItem;

public interface SpecItemMapper
{
  public void save(SpecItem specitem);
  
  public void update(SpecItem specitem);
  
  public void delete(@Param("id") String id);
  
  public SpecItem findById(@Param("id") String id);
  
  public List<SpecItem> findAll();
  
  public List<GoodType> getGoodTypes(@Param("id") String id);

}
