package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.District;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.PaginationSupport;

public interface DistrictService
  extends BaseService
{
  public void save(District district);
  
  public void update(District district);
  
  public void delete(Integer id);
  
  public District findById(Integer id);
  
  public List<District> findAll();
  
  public List<District> findAllRoot();
  
  public List<District> findByDistrict(District dc); 
  
  public List<District> findByDistrict(District dc,Integer startIndex,Integer pageSize); 
  
  public int getCountByDistrict(District dc);
  
  public PaginationSupport findPageByDistrict(District dc,Integer page,Integer pageSize);
  
}
