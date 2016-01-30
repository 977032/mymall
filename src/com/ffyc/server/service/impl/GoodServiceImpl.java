package com.ffyc.server.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.model.Good;
import com.ffyc.server.service.GoodService;
import com.ffyc.server.utils.PaginationSupport;

@Service("goodService")
public class GoodServiceImpl extends BaseServiceImpl implements GoodService {

    @Autowired
    private GoodMapper goodMapper;

	@Override
	public void save(Good good) {
		goodMapper.save(good);
	}
	
	@Override
	public void update(Good good) {
		goodMapper.update(good);
	}

	@Override
	public void delete(String id) {
		goodMapper.delete(id);
	}

	@Override
	public Good findById(String id) {
		return goodMapper.findById(id);
	}

	@Override
	public List<Good> findAll() {
		return goodMapper.findAll();
	}

	@Override
	public List<Good> findByGood(Good good) {
		return goodMapper.findByGood(good, null, null);
	}

	@Override
	public List<Good> findByGood(Good good, Integer startIndex,Integer pageSize) {
		return goodMapper.findByGood(good, startIndex, pageSize);
	}

	@Override
	public int getCountByGood(Good good) {
		return goodMapper.getCountByGood(good);
	}

	@Override
	public PaginationSupport findPageByGood(Good good, Integer page,
			Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.goodMapper.getCountByGood(good);
		List list = this.goodMapper.findByGood(good, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}

	@Override
	public PaginationSupport findPageBySql(String sql,String orderby, Integer page,
			Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.goodMapper.getCountBySql(sql);
		List list = this.goodMapper.findBySql(sql,orderby, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}
	
	

	@Override
	public List<Good> findBySql(String sql, String orderby) {
		List list = this.goodMapper.findBySql(sql,orderby, null,null);
		return list;
	}

	@Override
	public List<Good> findBySql(String sql, String orderby, Integer startIndex,Integer pageSize) {
		List list = this.goodMapper.findBySql(sql,orderby, startIndex,pageSize);
		return null;
	}

	@Override
	public int getCountBySql(String sql) {
		return this.goodMapper.getCountBySql(sql);
	}

	@Override
	public List<Good> findByLocalAndCategory(String locale,String category,String status,String[] keywords) {
		return this.goodMapper.findByLocalAndCategory(locale, category,status, keywords);
	}
	
	

}
