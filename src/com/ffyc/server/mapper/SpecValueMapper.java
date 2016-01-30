package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Spec;
import com.ffyc.server.model.SpecValue;

public interface SpecValueMapper
{
  public void save(SpecValue specValue);
  
  public void update(SpecValue specValue);
  
  public void delete(@Param("id") String id);
  
  public SpecValue findById(@Param("id") String id);
  
  public List<SpecValue> findAll();
  
  public List<SpecValue> findBySpecValue(@Param("specvalue") SpecValue specvalue,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountBySpecValue(@Param("specvalue") SpecValue specvalue);
  
  public List<Spec> getSpecs(@Param("id") String id);

}

