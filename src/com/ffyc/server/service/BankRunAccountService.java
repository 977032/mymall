package com.ffyc.server.service;

import java.util.Date;
import java.util.List;
import com.ffyc.server.dc.BankRunAccountDC;
import com.ffyc.server.dc.BankRunAccountDC;
import com.ffyc.server.model.BankRunAccount;
import com.ffyc.server.utils.PaginationSupport;

public interface BankRunAccountService
  extends BaseService
{
  public Double getTotalBalance(Date min, Date max);
  
  public void save(BankRunAccount bankRunAccount);
  
  public void update(BankRunAccount bankRunAccount);
  
  public void delete(String id);
  
  public BankRunAccount findById(String id);
  
  public List<BankRunAccount> findAll();
  
  public List<BankRunAccount> findByBankRunAccount(BankRunAccountDC dc);
  
  public List<BankRunAccount> findByBankRunAccount(BankRunAccountDC dc,Integer startIndex,Integer pageSize);
  
  public int getCountByBankRunAccount(BankRunAccountDC dc);
  
  public PaginationSupport findPageByBankRunAccount(BankRunAccountDC dc,Integer page, Integer pageSize) ;

}
