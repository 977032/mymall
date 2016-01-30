package com.ffyc.server.service;

import com.ffyc.server.model.ExtraAttr;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ExtraAttrService
  extends BaseService
{
  public void save(ExtraAttr extraattr);
  
  public void update(ExtraAttr extraattr);
  
  public void delete(String id);
  
  public ExtraAttr findById(String id);
  
  public List<ExtraAttr> findAll();
  
  public List<ExtraAttr> findAllByGoodType(String goodtype);
  
}
