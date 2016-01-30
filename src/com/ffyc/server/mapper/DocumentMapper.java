package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Document;
import com.ffyc.server.model.Good;

public interface DocumentMapper
{
  public void save(Document document);
  
  public void update(Document document);
  
  public void delete(@Param("id") String id);
  
  public Document findById(@Param("id") String id);

  public List<Document> findAll();
  
  public List<Document> findByDocument(@Param("document") Document document,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByDocument(@Param("document") Document document);
  
  public List<Document> findBySql(@Param("sql") String sql,
			@Param("orderby") String orderby,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

  public int getCountBySql(@Param("sql") String sql);
  
}
