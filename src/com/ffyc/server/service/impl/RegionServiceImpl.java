package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.RegionMapper;
import com.ffyc.server.model.Region;
import com.ffyc.server.service.RegionService;

@Service("regionService")
public class RegionServiceImpl extends BaseServiceImpl implements RegionService {

    @Autowired
    private RegionMapper regionMapper;
	

	@Override
	public void save(Region region) {
		regionMapper.save(region);
	}
	
	@Override
	public void update(Region region) {
		regionMapper.update(region);
	}


	@Override
	public void delete(String id) {
		regionMapper.delete(id);
	}

	@Override
	public Region findById(String id) {
		return regionMapper.findById(id);
	}

	@Override
	public List<Region> findAll() {
		return regionMapper.findAll();
	}

}
