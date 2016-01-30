package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.PointRunAccountMapper;
import com.ffyc.server.model.PointRunAccount;
import com.ffyc.server.service.PointRunAccountService;
import com.ffyc.server.utils.PaginationSupport;

@Service("pointRunAccountService")
public class PointRunAccountServiceImpl extends BaseServiceImpl implements PointRunAccountService {

    @Autowired
    private PointRunAccountMapper ponitRunAccountMapper;
	
	@Override
	public void save(PointRunAccount pointRunAccount) {
		ponitRunAccountMapper.save(pointRunAccount);
	}
	
	@Override
	public void update(PointRunAccount pointRunAccount) {
		ponitRunAccountMapper.update(pointRunAccount);
	}


	@Override
	public void delete(String id) {
		ponitRunAccountMapper.delete(id);
	}

	@Override
	public PointRunAccount findById(String id) {
		return ponitRunAccountMapper.findById(id);
	}

	@Override
	public List<PointRunAccount> findAll() {
		return ponitRunAccountMapper.findAll();
	}

	@Override
	public List<PointRunAccount> findByPointRunAccount(PointRunAccount dc) {
		return this.ponitRunAccountMapper.findByPointRunAccount(dc, null, null);
	}

	@Override
	public List<PointRunAccount> findByPointRunAccount(PointRunAccount dc,
			Integer startIndex, Integer pageSize) {
		return this.ponitRunAccountMapper.findByPointRunAccount(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByPointRunAccount(PointRunAccount dc) {
		return this.ponitRunAccountMapper.getCountByPointRunAccount(dc);
	}

	@Override
	public PaginationSupport findPageByPointRunAccount(PointRunAccount dc,
			Integer page, Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.ponitRunAccountMapper.getCountByPointRunAccount(dc);
		List list = this.ponitRunAccountMapper.findByPointRunAccount(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}
	
}
