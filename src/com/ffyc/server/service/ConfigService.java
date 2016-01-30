package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.Config;

public interface ConfigService
  extends BaseService
{
  public void save(Config config);
  
  public void update(Config config);
  
  public void delete(String id);
  
  public Config findById(String id);
  
  public Config findByProperty(String property);
  
  public List<Config> findAll();
  
  public List<Config> findAllRoot();
  
  public List<Config> findByConfig(Config config);
}
