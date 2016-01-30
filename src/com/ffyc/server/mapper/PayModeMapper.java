package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.PayMode;

public interface PayModeMapper
{
  public void save(PayMode paymode);
  
  public void update(PayMode paymode);
  
  public void delete(@Param("id") String id);
  
  public PayMode findById(@Param("id") String id);
  
  public List<PayMode> findAll();
}
