package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.model.Good;

public interface ExtraAttrValueMapper
{
  public void save(ExtraAttrValue extraattrvalue);
  
  public void update(ExtraAttrValue extraattrvalue);
  
  public void delete(@Param("id") String id);
  
  public ExtraAttrValue findById(@Param("id") String id);
  
  public List<ExtraAttrValue> findAll();
  
  public List<ExtraAttrValue> findByExtraAttrValue(@Param("eavalue") ExtraAttrValue eavalue,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByExtraAttrValue(@Param("eavalue") ExtraAttrValue eavalue);
  
  public List<Good> getGoods(@Param("id") String id);
  
}
