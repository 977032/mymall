package com.ffyc.server.service;

import com.ffyc.server.model.Order;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;

public interface OrderItemService
  extends BaseService
{
  public void save(OrderItem orderItem);
  
  public void update(OrderItem orderItem);
  
  public void delete(String id);
  
  public OrderItem findById(String id);
  
  public List<OrderItem> findAll();
  
  public List<OrderItem> findByOrderItem(OrderItem dc);
  
  public List<OrderItem> findByOrderItem(OrderItem dc,Integer startIndex,Integer pageSize);
  
  public int getCountByOrderItem(OrderItem dc);
  
  public PaginationSupport findPageByOrderItem(OrderItem dc,Integer page, Integer pageSize) ;
  
 
}
