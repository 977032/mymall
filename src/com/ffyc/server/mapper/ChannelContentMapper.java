package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.District;

public interface ChannelContentMapper
{
  public void save(ChannelContent channelContent);
  
  public void update(ChannelContent channelContent);
  
  public void delete(@Param("id") String id);
  
  public ChannelContent findById(@Param("id") String id);

  public List<ChannelContent> findAll();
  
  public List<ChannelContent> findByChannelContent(@Param("chcontent") ChannelContent chcontent,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByChannelContent(@Param("chcontent") ChannelContent chcontent);
  
}
