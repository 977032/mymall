package com.ffyc.server.service;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Document;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AttachmentService
  extends BaseService
{
  
  public void save(Attachment attachment);
  
  public void update(Attachment attachment);
  
  public void deleteAndFile(Attachment attachment);
  
  public void delete(String id);
  
  public Attachment findById(String id);
  
  public List<Attachment> findByAttachment(Attachment dc);
  
  public List<Attachment> findByAttachment(Attachment dc,Integer startIndex,Integer pageSize);
  
  public int getCountByAttachment(Attachment dc);
  
  public PaginationSupport findPageByAttachment(Attachment dc,Integer page, Integer pageSize) ;
  
  public List<Attachment> findAll();
  
  public List findAllTemplate();
}
