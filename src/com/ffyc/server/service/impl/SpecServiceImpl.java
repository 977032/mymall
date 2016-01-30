package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.SpecMapper;
import com.ffyc.server.model.Spec;
import com.ffyc.server.service.SpecService;

@Service("specService")
public class SpecServiceImpl extends BaseServiceImpl implements SpecService {

    @Autowired
    private SpecMapper specMapper;
	
	@Override
	public void save(Spec spec) {
		specMapper.save(spec);
	}
	
	@Override
	public void update(Spec spec) {
		specMapper.update(spec);
	}

	@Override
	public void delete(String id) {
		specMapper.delete(id);
	}

	@Override
	public Spec findById(String id) {
		return specMapper.findById(id);
	}

	@Override
	public List<Spec> findAll() {
		return specMapper.findAll();
	}

	@Override
	public List<Spec> findBySpec(Spec spec) {
		return specMapper.findBySpec(spec, null, null);
	}

	@Override
	public List<Spec> findBySpec(Spec spec, Integer startIndex, Integer pageSize) {
		return specMapper.findBySpec(spec, startIndex, pageSize);
	}

	@Override
	public int getCountBySpec(Spec spec) {
		return specMapper.getCountBySpec(spec);
	}
	
	
	
}
