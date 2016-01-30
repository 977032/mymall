package com.ffyc.server.service;

import java.util.List;
import com.ffyc.server.dc.OrderDC;
import com.ffyc.server.model.Order;
import com.ffyc.server.utils.PaginationSupport;

public interface OrderService
  extends BaseService
{
  public void save(Order order);
  
  public void update(Order order);
  
  public void delete(String id);
  
  public Order findById(String id);
    
  public List<Order> findAll();
  
  public List<Order> findByOrder(OrderDC dc);
  
  public List<Order> findByOrder(OrderDC dc,Integer startIndex,Integer pageSize);
  
  public int getCountByOrder(OrderDC dc);
  
  public PaginationSupport findPageByOrder(OrderDC dc,Integer page, Integer pageSize) ;
  
}
