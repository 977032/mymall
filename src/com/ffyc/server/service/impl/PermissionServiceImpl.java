package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.PermissionMapper;
import com.ffyc.server.model.Permission;
import com.ffyc.server.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl extends BaseServiceImpl implements PermissionService {


    @Autowired
    private PermissionMapper permissionMapper;
	
	@Override
	public void save(Permission permission) {
		permissionMapper.save(permission);
	}
	
	@Override
	public void update(Permission permission) {
		permissionMapper.update(permission);
	}

	@Override
	public void delete(String id) {
		permissionMapper.delete(id);
	}

	@Override
	public Permission findById(String id) {
		return permissionMapper.findById(id);
	}


	@Override
	public List<Permission> findAll() {
		return permissionMapper.findAll();
	}
	
	@Override
	public List<Permission> findByManagerAndCode(String manager, String code){
		return permissionMapper.findByManagerAndCode(manager, code);
		
	}
	@Override
	public List<Permission> findByMgroupAndCode(String mgroup, String code){
		return permissionMapper.findByMgroupAndCode(mgroup, code);
	}

	@Override
	public List<Permission> findAllRoot() {
		return permissionMapper.findAllRoot();
	}

	@Override
	public List<Permission> findByPermission(Permission permission) {
		return permissionMapper.findByPermission(permission);
	}
	
	@Override
	public List<Permission> findAllChild(String pid){
		return permissionMapper.findAllChild(pid);
	}

}
