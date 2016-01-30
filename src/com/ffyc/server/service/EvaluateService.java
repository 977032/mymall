package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.Evaluate;

public interface EvaluateService
  extends BaseService
{
  public void save(Evaluate evaluate);
  
  public void update(Evaluate evaluate);
  
  public void delete(String id);
  
  public Evaluate findById(String id);
  
  public List<Evaluate> findAll();
 
}
