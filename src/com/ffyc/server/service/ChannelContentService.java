package com.ffyc.server.service;

import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.Locale;
import com.ffyc.server.model.Member;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;

public interface ChannelContentService
  extends BaseService
{
  public void save(ChannelContent channelContent);
  
  public void update(ChannelContent channelContent);
  
  public void delete(String id);
  
  public ChannelContent findById(String id);
  
  public List<ChannelContent> findAll();
  
  public List<ChannelContent> findAllGlobal();
  
  public List<ChannelContent> findAllGlobal(Locale locale);
  
  public List<ChannelContent> findByChannelContent(ChannelContent chcontent); 
  
  public List<ChannelContent> findByChannelContent(ChannelContent chcontent,Integer startIndex,Integer pageSize); 
  
  public int getCountByChannelContent(ChannelContent chcontent);
  
  public PaginationSupport findPageByChannelContent(ChannelContent chcontent,Integer page,Integer pageSize);
  
  public void getLocales(List<ChannelContent> list);
  
  public void getDocumentCategorys(List<ChannelContent> list);
  
}
