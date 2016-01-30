package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Comment;

public interface CommentMapper
{
  public void save(Comment comment);
  
  public void update(Comment comment);
  
  public void delete(@Param("id") String id);
  
  public Comment findById(@Param("id") String id);

  public List<Comment> findAll();
  
  public List<Comment> findByComment(@Param("comment") Comment comment,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByComment(@Param("comment") Comment comment);
  
}
