package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.ConfigMapper;
import com.ffyc.server.model.Config;
import com.ffyc.server.service.ConfigService;

@Service("configService")
public class ConfigServiceImpl extends BaseServiceImpl implements ConfigService {


    @Autowired
    private ConfigMapper configMapper;
	
	@Override
	public void save(Config config) {
		configMapper.save(config);
	}
	
	@Override
	public void update(Config config) {
		configMapper.update(config);
	}

	@Override
	public void delete(String id) {
		configMapper.delete(id);
	}

	@Override
	public Config findById(String id) {
		return configMapper.findById(id);
	}

	
	@Override
	public Config findByProperty(String property) {
		return configMapper.findByProperty(property);
	}

	@Override
	public List<Config> findAll() {
		return configMapper.findAll();
	}

	@Override
	public List<Config> findAllRoot() {
		return configMapper.findAllRoot();
	}

	@Override
	public List<Config> findByConfig(Config config) {
		return configMapper.findByConfig(config);
	}


}
