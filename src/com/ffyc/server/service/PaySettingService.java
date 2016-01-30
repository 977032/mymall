package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.dc.PaySettingDC;
import com.ffyc.server.model.PaySetting;

public interface PaySettingService
  extends BaseService
{
  public void save(PaySetting paysetting);
  
  public void update(PaySetting paysetting);
  
  public void delete(String id);
  
  public PaySetting findById(String id);
  
  public PaySetting findByProperty(String property);
  
  public List<PaySetting> findByPaySetting(PaySettingDC paysetting);
  
  public List<PaySetting> findAll();
  
  public List<PaySetting> findAllRoot(String pmtype);
  
}