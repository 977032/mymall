package com.ffyc.server.service;

import java.util.List;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.Comment;
import com.ffyc.server.utils.PaginationSupport;

public interface CommentService
  extends BaseService
{
  public void save(Comment comment);
  
  public void update(Comment comment);
  
  public void delete(String id);
  
  public Comment findById(String id);
  
  public List<Comment> findAll();
  
  public List<Comment> findByComment(Comment dc);
  
  public List<Comment> findByComment(Comment dc,Integer startIndex,Integer pageSize);
  
  public int getCountByComment(Comment dc);
  
  public PaginationSupport findPageByComment(Comment dc,Integer page, Integer pageSize) ;
  
}
