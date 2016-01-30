package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.Store;

public interface StoreService
  extends BaseService
{
  public void save(Store store);
  
  public void update(Store store);
  
  public void delete(String id);
  
  public Store findById(String id);
  
  public List<Store> findAll();
  
}
