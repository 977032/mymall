package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Document;
import com.ffyc.server.model.Locale;

public interface AttachmentMapper
{
  public void save(Attachment attachment);
  
  public void update(Attachment attachment);
  
  public void delete(@Param("id") String id);
  
  public Attachment findById(@Param("id") String id);
  
  public List<Attachment> findByAttachment(@Param("attachment") Attachment attachment,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
   
  public int getCountByAttachment(@Param("attachment") Attachment attachment);
  
  public List<Attachment> findAll();
  
  public List<Attachment> findAllTemplate();
  
  public List<Attachment> findByIds(List<String> ids);
  
}

