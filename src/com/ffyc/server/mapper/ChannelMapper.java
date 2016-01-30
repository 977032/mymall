package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Channel;

public interface ChannelMapper
{
  public void save(Channel channel);
  
  public void update(Channel channel);
  
  public void delete(@Param("id") String id);
  
  public Channel findById(@Param("id") String id);
  
  public List<Channel> findAll();
  
}
