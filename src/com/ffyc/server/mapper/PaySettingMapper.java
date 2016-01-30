package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.dc.PaySettingDC;
import com.ffyc.server.model.PaySetting;

public interface PaySettingMapper 
{
  public void save(PaySetting paysetting);
  
  public void update(PaySetting paysetting);
  
  public void delete(@Param("id") String id);
  
  public PaySetting findById(@Param("id") String id);
  
  public PaySetting findByProperty(String property);
  
  public List<PaySetting> findByPaySetting(PaySettingDC paysetting);
  
  public List<PaySetting> findAll();
  
  public List<PaySetting> findAllRoot(@Param("pmtype") String pmtype);

}