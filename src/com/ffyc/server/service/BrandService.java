package com.ffyc.server.service;

import com.ffyc.server.model.Brand;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.Document;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;
import java.util.Set;

public interface BrandService
  extends BaseService
{
  public void save(Brand brand);
  
  public void update(Brand brand);
  
  public void delete(String id);
  
  public Brand findById(String id);
  
  public List<Brand> findAll();
  
  public List<Brand> findByBrand(Brand brand);
  
  public List<Brand> findByBrand(Brand brand,Integer startIndex,Integer pageSize); 
  
  public int getCountByBrand(Brand brand);
  
  public PaginationSupport findPageByBrand(Brand brand, Integer page,Integer pageSize);
  
  public void getLogoos(List<Brand> list);
  
}
