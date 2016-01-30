package com.ffyc.server.service;

import com.ffyc.server.model.Document;
import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Locale;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface GoodService
  extends BaseService
{
  public void save(Good good);
  
  public void update(Good good);
  
  public void delete(String id);
  
  public Good findById(String id);
  
  public List<Good> findAll();
  
  public List<Good> findByGood(Good good);
  
  public List<Good> findByGood(Good good,Integer startIndex,Integer pageSize); 
  
  public int getCountByGood(Good good);
  
  public PaginationSupport findPageByGood(Good good, Integer page,Integer pageSize);
  
  public List<Good> findBySql(String sql,String orderby);
  
  public List<Good> findBySql(String sql,String orderby,Integer startIndex,Integer pageSize); 
  
  public PaginationSupport findPageBySql(String sql,String orderby, Integer page,Integer pageSize);
  
  public int getCountBySql(String sql);
  
  public List<Good> findByLocalAndCategory(String locale,String category,String status,String[] keywords);

  
}
