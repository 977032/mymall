package com.ffyc.server.service;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.SpecValue;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;

public interface SpecValueService
  extends BaseService
{
  
  public void save(SpecValue specValue);
  
  public void update(SpecValue specValue);
  
  public void delete(String id);
  
  public SpecValue findById(String id);
  
  public List<SpecValue> findAll();
  
  public List<SpecValue> findBySpecValue(SpecValue dc);
  
  public List<SpecValue> findBySpecValue(SpecValue dc,Integer startIndex,Integer pageSize);
  
  public int getCountBySpecValue(SpecValue dc);
  
  public PaginationSupport findPageBySpecValue(SpecValue dc,Integer page, Integer pageSize) ;
  
  public void getImagees(List<SpecValue> list) ;
}
