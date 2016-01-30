package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.AreaMapper;
import com.ffyc.server.model.Area;
import com.ffyc.server.service.AreaService;
import com.ffyc.server.utils.PaginationSupport;

@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;
	
	@Override
	public void save(Area area) {
		areaMapper.save(area);
	}
	
	@Override
	public void update(Area area) {
		areaMapper.update(area);
	}

	@Override
	public void delete(String id) {
		areaMapper.delete(id);
	}

	@Override
	public Area findById(String id) {
		return areaMapper.findById(id);
	}

	@Override
	public List<Area> findAll() {
		return areaMapper.findAll();
	}

	@Override
	public List<Area> findByArea(Area area, Integer startIndex, Integer pageSize) {
		return areaMapper.findByArea(area, startIndex, pageSize);
	}

	@Override
	public int getCountByArea(Area area) {
		return areaMapper.getCountByArea(area);
	}

	@Override
	public PaginationSupport findPageByArea(Area area, Integer page,Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex =  (page-1) * pageSize;
		int totalCount = this.areaMapper.getCountByArea(area);
		List list = this.areaMapper.findByArea(area, startIndex,pageSize);
		PaginationSupport ps = new PaginationSupport(list,totalCount,startIndex,pageSize);
		return ps;
	}
	
}
