package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.LogisticMapper;
import com.ffyc.server.model.Logistic;
import com.ffyc.server.service.LogisticService;
import com.ffyc.server.utils.PaginationSupport;

@Service("logisticService")
public class LogisticServiceImpl extends BaseServiceImpl implements
		LogisticService {

	@Autowired
	private LogisticMapper logisticMapper;

	@Override
	public void save(Logistic logistic) {
		logisticMapper.save(logistic);
	}

	@Override
	public void update(Logistic logistic) {
		logisticMapper.update(logistic);
	}

	@Override
	public void delete(String id) {
		logisticMapper.delete(id);
	}

	@Override
	public Logistic findById(String id) {
		return logisticMapper.findById(id);
	}

	@Override
	public List<Logistic> findAll() {
		return logisticMapper.findAll();
	}

	@Override
	public List<Logistic> findByLogistic(Logistic logistic, Integer startIndex, Integer pageSize) {
		return logisticMapper.findByLogistic(logistic, startIndex, pageSize);
	}

	@Override
	public int getCountByLogistic(Logistic logistic) {
		return logisticMapper.getCountByLogistic(logistic);
	}

	@Override
	public PaginationSupport findPageByLogistic(Logistic logistic,
			Integer page,Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.logisticMapper.getCountByLogistic(logistic);
		List list = this.logisticMapper.findByLogistic(logistic, startIndex,pageSize
				);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}

}
