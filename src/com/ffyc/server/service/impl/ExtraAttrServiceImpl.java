package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.ExtraAttrMapper;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.service.ExtraAttrService;

@Service("extraAttrService")
public class ExtraAttrServiceImpl extends BaseServiceImpl implements ExtraAttrService {


    @Autowired
    private ExtraAttrMapper extraAttrMapper;
	
	@Override
	public void save(ExtraAttr extraattr) {
		extraAttrMapper.save(extraattr);
	}
	
	@Override
	public void update(ExtraAttr extraattr) {
		extraAttrMapper.update(extraattr);
	}

	@Override
	public void delete(String id) {
		extraAttrMapper.delete(id);
	}

	@Override
	public ExtraAttr findById(String id) {
		return extraAttrMapper.findById(id);
	}

	@Override
	public List<ExtraAttr> findAll() {
		return extraAttrMapper.findAll();
	}

	@Override
	public List<ExtraAttr> findAllByGoodType(String goodtype) {
		return extraAttrMapper.findAllByGoodType(goodtype);
	}
	
	

}
