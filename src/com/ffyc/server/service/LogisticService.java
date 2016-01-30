package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.Logistic;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.PaginationSupport;

public interface LogisticService
  extends BaseService
{
  public void save(Logistic logistic);
  
  public void update(Logistic logistic);
  
  public void delete(String id);
  
  public Logistic findById(String id);
  
  public List<Logistic> findAll();
  
  public List<Logistic> findByLogistic(Logistic logistic,Integer startIndex,Integer pageSize); 
  
  public int getCountByLogistic(Logistic logistic);
  
  public PaginationSupport findPageByLogistic(Logistic logistic, Integer page,Integer pageSize);
  
}
