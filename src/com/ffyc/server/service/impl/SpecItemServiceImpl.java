package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.SpecItemMapper;
import com.ffyc.server.model.SpecItem;
import com.ffyc.server.service.SpecItemService;

@Service("specItemService")
public class SpecItemServiceImpl extends BaseServiceImpl implements SpecItemService {


    @Autowired
    private SpecItemMapper specItemMapper;
	
	
	@Override
	public void save(SpecItem specitem) {
		specItemMapper.save(specitem);
	}
	
	@Override
	public void update(SpecItem specitem) {
		specItemMapper.update(specitem);
	}

	@Override
	public void delete(String id) {
		specItemMapper.delete(id);
	}

	@Override
	public SpecItem findById(String id) {
		return specItemMapper.findById(id);
	}

	@Override
	public List<SpecItem> findAll() {
		return specItemMapper.findAll();
	}
	
}
