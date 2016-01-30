package com.ffyc.server.service;

import com.ffyc.server.model.Locale;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LocaleService
  extends BaseService
{
  public void save(Locale locale);
  
  public void update(Locale locale);
  
  public void delete(String id);
  
  public Locale findById(String id);
  
  public List<Locale> findAll();
  
}