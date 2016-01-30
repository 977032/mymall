package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Logistic;
import com.ffyc.server.model.Member;

public interface LogisticMapper
{
  public void save(Logistic logistic);
  
  public void update(Logistic logistic);
  
  public void delete(@Param("id") String id);
  
  public Logistic findById(@Param("id") String id);
  
  public List<Logistic> findAll();
  
  public List<Logistic> findByLogistic(@Param("logistic") Logistic logistic,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByLogistic(@Param("logistic") Logistic logistic);
  
}
