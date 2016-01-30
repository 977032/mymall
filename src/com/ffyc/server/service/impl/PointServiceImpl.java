package com.ffyc.server.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.model.Point;
import com.ffyc.server.mapper.PointMapper;
import com.ffyc.server.model.Point;
import com.ffyc.server.service.PointService;

@Service("pointService")
public class PointServiceImpl extends BaseServiceImpl implements PointService {

    @Autowired
    private PointMapper ponitMapper;
	
	@Override
	public void save(Point point) {
		ponitMapper.save(point);
	}
	
	@Override
	public void update(Point point) {
		ponitMapper.update(point);
	}


	@Override
	public void delete(String id) {
		ponitMapper.delete(id);
	}

	@Override
	public Point findById(String id) {
		return ponitMapper.findById(id);
	}

	@Override
	public List<Point> findAll() {
		return ponitMapper.findAll();
	}

	@Override
	public Point openAccount() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
	    String randint = RandomStringUtils.randomNumeric(8);
	    Timestamp ts = new Timestamp(System.currentTimeMillis());
	    Point point = new Point();
	    point.setId(uuid);
	    point.setAccount("88888888" + randint);
	    point.setStatus(Point.STATUS_NORMAL);
	    point.setBalance(Integer.valueOf(0));
	    point.setRemarks("资金账户开户。");
	    point.setCtime(ts);
	    ponitMapper.save(point);
	    return point;
	}
	
}
