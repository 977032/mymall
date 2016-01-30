package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.PointRunAccount;
import com.ffyc.server.utils.PaginationSupport;

public interface PointRunAccountService
  extends BaseService
{
  public void save(PointRunAccount pointRunAccount);
  
  public void update(PointRunAccount pointRunAccount);
  
  public void delete(String id);
  
  public PointRunAccount findById(String id);
  
  public List<PointRunAccount> findAll();
  
  public List<PointRunAccount> findByPointRunAccount(PointRunAccount dc);
  
  public List<PointRunAccount> findByPointRunAccount(PointRunAccount dc,Integer startIndex,Integer pageSize);
  
  public int getCountByPointRunAccount(PointRunAccount dc);
  
  public PaginationSupport findPageByPointRunAccount(PointRunAccount dc,Integer page, Integer pageSize) ;

}
