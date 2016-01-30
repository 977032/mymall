package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Bank;

public interface BankMapper
{
  public void save(Bank bank);
  
  public void update(Bank bank);
  
  public void delete(@Param("id") String id);
  
  public Bank findById(@Param("id") String id);
  
  public List<Bank> findAll();
}
