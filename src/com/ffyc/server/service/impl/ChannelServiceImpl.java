package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.ChannelMapper;
import com.ffyc.server.model.Channel;
import com.ffyc.server.service.ChannelService;

@Service("channelService")
public class ChannelServiceImpl extends BaseServiceImpl implements ChannelService {

    @Autowired
    private ChannelMapper channelMapper;
	
	@Override
	public void save(Channel channel) {
		channelMapper.save(channel);
	}
	
	@Override
	public void update(Channel channel) {
		channelMapper.update(channel);
	}

	@Override
	public void delete(String id) {
		channelMapper.delete(id);
	}

	@Override
	public Channel findById(String id) {
		return channelMapper.findById(id);
	}

	@Override
	public List<Channel> findAll() {
		return channelMapper.findAll();
	}

}
