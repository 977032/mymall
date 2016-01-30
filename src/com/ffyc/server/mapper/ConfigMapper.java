package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Config;

public interface ConfigMapper
{
  public void save(Config config);
  
  public void update(Config config);
  
  public void delete(@Param("id") String id);
  
  public Config findById(@Param("id") String id);
  
  public Config findByProperty(@Param("property") String property);
  
  public List<Config> findAll();
  
  public List<Config> findAllRoot();
  
  public List<Config> findAllChild(@Param("pid") String pid);
  
  public List<Config> findByConfig(Config config);
  
}
