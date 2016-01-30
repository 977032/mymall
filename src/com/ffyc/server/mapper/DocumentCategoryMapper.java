package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Category;
import com.ffyc.server.model.DocumentCategory;

public interface DocumentCategoryMapper
{
  public void save(DocumentCategory documentCategory);
  
  public void update(DocumentCategory documentCategory);
  
  public void delete(@Param("id") String id);
  
  public DocumentCategory findById(@Param("id") String id);
  
  public List<DocumentCategory> findByIds(List<String> ids);

  public List<DocumentCategory> findAll();
  
  public List<DocumentCategory> findByDocumentCategory(DocumentCategory dc);
  
}
