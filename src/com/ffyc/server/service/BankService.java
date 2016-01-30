package com.ffyc.server.service;

import com.ffyc.server.model.Bank;
import com.ffyc.server.model.Brand;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

public interface BankService
  extends BaseService
{
  public Bank openAccount();
  
  public void save(Bank bank);
  
  public void update(Bank bank);
  
  public void delete(String id);
  
  public Bank findById(String id);
  
  public List<Bank> findAll();
  
}
