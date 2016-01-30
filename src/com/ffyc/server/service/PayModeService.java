package com.ffyc.server.service;

import com.ffyc.server.model.PayMode;
import java.util.List;

public interface PayModeService
  extends BaseService
{
  public void save(PayMode paymode);
  
  public void update(PayMode paymode);
  
  public void delete(String id);
  
  public PayMode findById(String id);
  
  public List<PayMode> findAll();
  
}

