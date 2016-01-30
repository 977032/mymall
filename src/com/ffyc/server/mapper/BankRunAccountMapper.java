package com.ffyc.server.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.dc.BankRunAccountDC;
import com.ffyc.server.model.BankRunAccount;

public interface BankRunAccountMapper
{
  public void save(BankRunAccount bankRunAccount);
  
  public void update(BankRunAccount bankRunAccount);
  
  public void delete(@Param("id") String id);
  
  public BankRunAccount findById(@Param("id") String id);
  
  public List<BankRunAccount> findAll();
  
  public Double getTotalBalance(@Param("status") String status, @Param("min") Date min,@Param("max") Date max);
  
  public List<BankRunAccount> findByBankRunAccount(@Param("braccount") BankRunAccountDC braccount,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByBankRunAccount(@Param("braccount") BankRunAccountDC braccount);
}