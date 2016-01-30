package com.ffyc.server.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Locale;
import com.ffyc.server.utils.PaginationSupport;

public interface DocumentCategoryService
  extends BaseService
{
  
  public ArrayList<DocumentCategory> findAllTree();
  
  public ArrayList<DocumentCategory> findSubTree(ArrayList<DocumentCategory> list, DocumentCategory doccate);
  
  public void save(DocumentCategory documentCategory);
  
  public void update(DocumentCategory documentCategory);
  
  public void delete(String id);
  
  public DocumentCategory findById(String id);
  
  public List<DocumentCategory> findAll();
  
  public List<DocumentCategory> findAllRoot();
  
  public List<DocumentCategory> findAllRoot(Locale locale);
  
  public List<DocumentCategory> findByDocumentCategory(DocumentCategory doccate);
  
}
