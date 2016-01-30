package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Order;

public interface OrderMapper
{
  public void save(Order order);
  
  public void update(Order order);
  
  public void delete(@Param("id") String id);
  
  public Order findById(@Param("id") String id);
  
  public List<Order> findAll();
  
  public List<Order> findByOrder(@Param("order") Order order,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByOrder(@Param("order") Order order);
  
}
