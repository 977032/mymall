package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.StoreMapper;
import com.ffyc.server.model.Store;
import com.ffyc.server.service.StoreService;

@Service("storeService")
public class StoreServiceImpl extends BaseServiceImpl implements StoreService {

    @Autowired
    private StoreMapper storeMapper;
	
	@Override
	public void save(Store store) {
		storeMapper.save(store);
	}
	
	@Override
	public void update(Store store) {
		storeMapper.update(store);
	}

	@Override
	public void delete(String id) {
		storeMapper.delete(id);
	}

	@Override
	public Store findById(String id) {
		return storeMapper.findById(id);
	}

	@Override
	public List<Store> findAll() {
		return storeMapper.findAll();
	}

}
