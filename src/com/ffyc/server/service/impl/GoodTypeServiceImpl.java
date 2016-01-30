package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.GoodTypeMapper;
import com.ffyc.server.model.Brand;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.GoodType;
import com.ffyc.server.model.SpecItem;
import com.ffyc.server.service.GoodTypeService;

@Service("goodTypeService")
public class GoodTypeServiceImpl extends BaseServiceImpl implements GoodTypeService {

    @Autowired
    private GoodTypeMapper goodTypeMapper;
	
	@Override
	public void save(GoodType goodtype) {
		goodTypeMapper.save(goodtype);
	}
	
	@Override
	public void update(GoodType goodtype) {
		goodTypeMapper.update(goodtype);
	}

	@Override
	public void delete(String id) {
		goodTypeMapper.delete(id);
	}

	@Override
	public GoodType findById(String id) {
		return goodTypeMapper.findById(id);
	}

	@Override
	public List<GoodType> findAll() {
		return goodTypeMapper.findAll();
	}
}
