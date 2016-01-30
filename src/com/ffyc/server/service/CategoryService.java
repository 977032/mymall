package com.ffyc.server.service;

import com.ffyc.server.model.Category;
import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Locale;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.PaginationSupport;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService
  extends BaseService
{ 
  public ArrayList<Category> findAllTree();
  
  public ArrayList<Category> findAllTree(Locale local);
  
  public ArrayList<Category> findSubTree(ArrayList<Category> list, Category category);
  
  public void save(Category category);
  
  public void update(Category category);
  
  public void delete(String id);
  
  public Category findById(String id);
  
  public List<Category>  findAll();
  
  public List<Category>  findAllRoot();
  
  public List<Category>  findAllRoot(Locale locale);
  
  public List<Category>  findByCategory(Category category); 
  
  public List<Category>  findByCategory(Category category,Integer startIndex,Integer pageSize); 
  
  public int getCountByCategory(Category category);
  
  public PaginationSupport findPageByCategory(Category category,Integer page,Integer pageSize);
  
  public void getLocales(List<Category> list);
  
  public void getImagees(List<Category> list);
  
  public List<Category> findBySql(String sql,String orderby);
  
  public List<Category> findBySql(String sql,String orderby,Integer startIndex,Integer pageSize); 
  
  public PaginationSupport findPageBySql(String sql,String orderby, Integer page,Integer pageSize);
  
  public int getCountBySql(String sql);
  
}
