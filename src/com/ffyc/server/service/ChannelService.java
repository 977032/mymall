package com.ffyc.server.service;

import com.ffyc.server.model.Channel;
import java.util.List;

public interface ChannelService
  extends BaseService
{
  public void save(Channel channel);
  
  public void update(Channel channel);
  
  public void delete(String id);
  
  public Channel findById(String id);
  
  public List<Channel> findAll();

}
