package com.ffyc.server.service;

import com.ffyc.server.model.Good;
import com.ffyc.server.model.Spec;

import java.util.List;

public interface SpecService
  extends BaseService
{
  public void save(Spec spec);
  
  public void update(Spec spec);
  
  public void delete(String id);
  
  public Spec findById(String id);
  
  public List<Spec> findAll();
  
  public List<Spec> findBySpec(Spec spec);
  
  public List<Spec> findBySpec(Spec spec,Integer startIndex,Integer pageSize); 
  
  public int getCountBySpec(Spec spec);

}
