package com.ffyc.server.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Document;
import com.ffyc.server.model.Logistic;
import com.ffyc.server.utils.PaginationSupport;

public interface DocumentService
  extends BaseService
{
  public void save(Document document);
  
  public void update(Document document);
  
  public void delete(String id);
  
  public Document findById(String id);
  
  public List<Document> findAll();
  
  public List<Document> findByDocument(Document document);
  
  public List<Document> findByDocument(Document document,Integer startIndex,Integer pageSize); 
  
  public int getCountByDocument(Document document);
  
  public PaginationSupport findPageByDocument(Document document, Integer page,Integer pageSize);
  
  public List<Document> findByDocumentOrNodePathLike(String locale,
			String doccate,String marker);

}
