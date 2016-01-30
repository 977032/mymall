package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.DistrictMapper;
import com.ffyc.server.model.District;
import com.ffyc.server.service.DistrictService;
import com.ffyc.server.utils.PaginationSupport;

@Service("districtService")
public class DistrictServiceImpl extends BaseServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

	@Override
	public void save(District district) {
		districtMapper.save(district);
	}
	
	@Override
	public void update(District district) {
		districtMapper.update(district);
	}

	@Override
	public void delete(Integer id) { 
		districtMapper.delete(id);
	}

	@Override
	public District findById(Integer id) {
		return districtMapper.findById(id);
	}

	
	@Override
	public List<District> findAllRoot() {
		return districtMapper.findAllRoot();
	}

	@Override
	public List<District> findAll() {
		return districtMapper.findAll();
	}

	@Override
	public List<District> findByDistrict(District dc) {
		return districtMapper.findByDistrict(dc, null, null);
	}

	@Override
	public List<District> findByDistrict(District dc,Integer startIndex, Integer pageSize) {
		return districtMapper.findByDistrict(dc, startIndex,pageSize );
	}

	@Override
	public int getCountByDistrict(District district) {
		return districtMapper.getCountByDistrict(district);
	}

	@Override
	public PaginationSupport findPageByDistrict(District dc,
			Integer page,Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex =  (page-1) * pageSize;
		int totalCount = this.districtMapper.getCountByDistrict(dc);
		List list = this.districtMapper.findByDistrict(dc,startIndex,pageSize);
		PaginationSupport ps = new PaginationSupport(list,totalCount,startIndex,pageSize);
		return ps;
	}

}
