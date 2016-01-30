package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Document;
import com.ffyc.server.model.Evaluate;

public interface EvaluateMapper
{
  public void save(Evaluate evaluate);
  
  public void update(Evaluate evaluate);
  
  public void delete(@Param("id") String id);
  
  public Evaluate findById(@Param("id") String id);
  
  public List<Evaluate> findAll();
  
  public List<Evaluate> findByEvaluate(@Param("evaluate") Evaluate evaluate,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByEvaluate(@Param("evaluate") Evaluate evaluate);
  
}
