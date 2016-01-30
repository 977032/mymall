package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.OrderItemMapper;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.service.OrderItemService;
import com.ffyc.server.utils.PaginationSupport;

@Service("orderItemService")
public class OrderItemServiceImpl extends BaseServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;
	
	@Override
	public void save(OrderItem orderItem) {
		orderItemMapper.save(orderItem);
	}
	
	@Override
	public void update(OrderItem orderItem) {
		orderItemMapper.update(orderItem);
	}

	@Override
	public void delete(String id) {
		orderItemMapper.delete(id);
	}

	@Override
	public OrderItem findById(String id) {
		return orderItemMapper.findById(id);
	}

	@Override
	public List<OrderItem> findAll() {
		return orderItemMapper.findAll();
	}

	@Override
	public List<OrderItem> findByOrderItem(OrderItem dc) {
		return this.orderItemMapper.findByOrderItem(dc, null, null);
	}

	@Override
	public List<OrderItem> findByOrderItem(OrderItem dc, Integer startIndex,
			Integer pageSize) {
		return this.orderItemMapper.findByOrderItem(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByOrderItem(OrderItem dc) {
		return this.orderItemMapper.getCountByOrderItem(dc);
	}

	@Override
	public PaginationSupport findPageByOrderItem(OrderItem dc, Integer page,
			Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.orderItemMapper.getCountByOrderItem(dc);
		List list = this.orderItemMapper.findByOrderItem(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}
}
