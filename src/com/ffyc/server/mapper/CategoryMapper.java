package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Category;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Member;

public interface CategoryMapper
{
  public void save(Category category);
  
  public void update(Category category);
  
  public void delete(@Param("id") String id);
  
  public Category findById(@Param("id") String id);

  public List<Category> findAll();
  
  public List<Category> findByCategory(@Param("category") Category category,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByCategory(@Param("category") Category category);
  
  public List<Category> findBySql(@Param("sql") String sql,@Param("orderby") String orderby,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

  public int getCountBySql(@Param("sql") String sql);
  
}
