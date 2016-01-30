package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Order;
import com.ffyc.server.model.PointRunAccount;

public interface PointRunAccountMapper
{
  public void save(PointRunAccount pointRunAccount);
  
  public void update(PointRunAccount pointRunAccount);
  
  public void delete(@Param("id") String id);
  
  public PointRunAccount findById(@Param("id") String id);
  
  public List<PointRunAccount> findAll();
  
  public List<PointRunAccount> findByPointRunAccount(@Param("prunacc") PointRunAccount prunacc,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByPointRunAccount(@Param("prunacc") PointRunAccount prunacc);
}