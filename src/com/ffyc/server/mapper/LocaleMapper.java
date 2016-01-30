package com.ffyc.server.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Locale;

public  interface LocaleMapper
{
  public void save(Locale locale);
  
  public void update(Locale locale);
  
  public void delete(@Param("id") String id);
  
  public Locale findById(@Param("id") String id);
  
  public List<Locale> findByIds(List<String> ids);
  
  public List<Locale> findAll();
}
