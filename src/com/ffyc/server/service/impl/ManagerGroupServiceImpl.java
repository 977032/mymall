package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.ManagerGroupMapper;
import com.ffyc.server.model.ManagerGroup;
import com.ffyc.server.service.ManagerGroupService;

@Service("managerGroupService")
public class ManagerGroupServiceImpl extends BaseServiceImpl implements ManagerGroupService {

    @Autowired
    private ManagerGroupMapper managerGroupMapper;

	@Override
	public void save(ManagerGroup mgroup) {
		managerGroupMapper.save(mgroup);
	}
	
	@Override
	public void update(ManagerGroup mgroup) {
		managerGroupMapper.update(mgroup);
	}


	@Override
	public void delete(String id) {
		managerGroupMapper.delete(id);
	}

	@Override
	public ManagerGroup findById(String id) {
		return managerGroupMapper.findById(id);
	}
	
	@Override
	public ManagerGroup findByCode(String code) {
		return managerGroupMapper.findByCode(code);
	}

	@Override
	public List<ManagerGroup> findAll() {
		return managerGroupMapper.findAll();
	}

	
}
