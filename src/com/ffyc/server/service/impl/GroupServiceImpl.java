package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.GroupMapper;
import com.ffyc.server.model.Group;
import com.ffyc.server.service.GroupService;

@Service("groupService")
public class GroupServiceImpl extends BaseServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

	@Override
	public void save(Group group) {
		groupMapper.save(group);
	}
	
	@Override
	public void update(Group group) {
		groupMapper.update(group);
	}

	@Override
	public void delete(String id) {
		groupMapper.delete(id);
	}

	@Override
	public Group findById(String id) {
		return groupMapper.findById(id);
	}

	@Override
	public List<Group> findAll() {
		return groupMapper.findAll();
	}

}
