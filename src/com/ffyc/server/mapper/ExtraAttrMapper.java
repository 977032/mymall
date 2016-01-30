package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.GoodType;

public interface ExtraAttrMapper
{
  public void save(ExtraAttr extraattr);
  
  public void update(ExtraAttr extraattr);
  
  public void delete(@Param("id") String id);
  
  public ExtraAttr findById(@Param("id") String id);
  
  public List<ExtraAttr> findAll();
  
  public List<ExtraAttr> findAllByGoodType(@Param("goodtype") String goodtype);
  
  public List<GoodType> getGoodTypes(@Param("id") String id);
}
