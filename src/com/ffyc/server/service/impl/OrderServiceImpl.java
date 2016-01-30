package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.dc.OrderDC;
import com.ffyc.server.mapper.OrderMapper;
import com.ffyc.server.model.Order;
import com.ffyc.server.service.OrderService;
import com.ffyc.server.utils.PaginationSupport;

@Service("orderService")
public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
	
	@Override
	public void save(Order order) {
		orderMapper.save(order);
	}
	
	@Override
	public void update(Order order) {
		orderMapper.update(order);
	}

	@Override
	public void delete(String id) {
		orderMapper.delete(id);
	}

	@Override
	public Order findById(String id) {
		return orderMapper.findById(id);
	}

	@Override
	public List<Order> findAll() {
		return orderMapper.findAll();
	}

	@Override
	public List<Order> findByOrder(OrderDC dc) {
		return orderMapper.findByOrder(dc, null, null);
	}

	@Override
	public List<Order> findByOrder(OrderDC dc, Integer startIndex,
			Integer pageSize) {
		return orderMapper.findByOrder(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByOrder(OrderDC dc) {
		return orderMapper.getCountByOrder(dc);
	}

	@Override
	public PaginationSupport findPageByOrder(OrderDC dc, Integer page,
			Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.orderMapper.getCountByOrder(dc);
		List list = this.orderMapper.findByOrder(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}
	
	
	
}
