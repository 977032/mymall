package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.OrderItem;

public interface OrderItemMapper
{
  public void save(OrderItem orderItem);
  
  public void update(OrderItem orderItem);
  
  public void delete(@Param("id") String id);
  
  public OrderItem findById(@Param("id") String id);
  
  public List<OrderItem> findAll();
  
  public List<OrderItem> findByOrderItem(@Param("orderitem") OrderItem orderitem,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByOrderItem(@Param("orderitem") OrderItem orderitem);
}
