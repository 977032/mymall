package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.utils.PaginationSupport;

public interface ExtraAttrValueService
  extends BaseService
{
  public void save(ExtraAttrValue extraattrvalue);
  
  public void update(ExtraAttrValue extraattrvalue);
  
  public void delete(String id);
  
  public ExtraAttrValue findById(String id);
  
  public List<ExtraAttrValue> findAll();
  
  public List<ExtraAttrValue> findByExtraAttrValue(ExtraAttrValue dc);
  
  public List<ExtraAttrValue> findByExtraAttrValue(ExtraAttrValue dc,Integer startIndex,Integer pageSize);
  
  public int getCountByExtraAttrValue(ExtraAttrValue dc);
  
  public PaginationSupport findPageByExtraAttrValue(ExtraAttrValue dc,Integer page, Integer pageSize) ;
 
}
