package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.ExtraAttrValueMapper;
import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.service.ExtraAttrValueService;
import com.ffyc.server.utils.PaginationSupport;

@Service("extraAttrValueService")
public class ExtraAttrValueServiceImpl extends BaseServiceImpl implements ExtraAttrValueService {

    @Autowired
    private ExtraAttrValueMapper extraAttrValueMapper;

	@Override
	public void save(ExtraAttrValue extraattrvalue) {
		extraAttrValueMapper.save(extraattrvalue);
	}
	
	@Override
	public void update(ExtraAttrValue extraattrvalue) {
		extraAttrValueMapper.update(extraattrvalue);
	}

	@Override
	public void delete(String id) {
		extraAttrValueMapper.delete(id);
	}

	@Override
	public ExtraAttrValue findById(String id) {
		return extraAttrValueMapper.findById(id);
	}

	@Override
	public List<ExtraAttrValue> findAll() {
		return extraAttrValueMapper.findAll();
	}

	@Override
	public List<ExtraAttrValue> findByExtraAttrValue(ExtraAttrValue dc) {
		return extraAttrValueMapper.findByExtraAttrValue(dc, null, null);
	}

	@Override
	public List<ExtraAttrValue> findByExtraAttrValue(ExtraAttrValue dc,
			Integer startIndex, Integer pageSize) {
		return extraAttrValueMapper.findByExtraAttrValue(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByExtraAttrValue(ExtraAttrValue dc) {
		return extraAttrValueMapper.getCountByExtraAttrValue(dc);
	}

	@Override
	public PaginationSupport findPageByExtraAttrValue(ExtraAttrValue dc,
			Integer page, Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.extraAttrValueMapper.getCountByExtraAttrValue(dc);
		List list = this.extraAttrValueMapper.findByExtraAttrValue(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}

}
