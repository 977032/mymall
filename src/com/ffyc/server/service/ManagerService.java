package com.ffyc.server.service;

import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;

public interface ManagerService
  extends BaseService
{
  public Notice login(String account, String password, String validateCode);
  
  public void save(Manager manager);
  
  public void update(Manager manager);
  
  public void delete(String id);
  
  public Manager findById(String id);
  
  public Manager findByAccount(String account);
  
  public List<Manager> findAll();
  
  public List<Manager> findByManager(Manager dc); 
  
  public List<Manager> findByManager(Manager dc,Integer startIndex,Integer pageSize); 
  
  public int getCountByManager(Manager dc);
  
  public PaginationSupport findPageByManager(Manager dc,Integer page,Integer pageSize);
  
}

