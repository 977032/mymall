package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.PayModeMapper;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.service.PayModeService;

@Service("paymodeService")
public class PayModeServiceImpl extends BaseServiceImpl implements PayModeService {

    @Autowired
    private PayModeMapper payModeMapper;
	
	@Override
	public void save(PayMode paymode) {
		payModeMapper.save(paymode);
	}
	
	@Override
	public void update(PayMode paymode) {
		payModeMapper.update(paymode);
	}

	@Override
	public void delete(String id) {
		payModeMapper.delete(id);
	}

	@Override
	public PayMode findById(String id) {
		return payModeMapper.findById(id);
	}

	@Override
	public List<PayMode> findAll() {
		return payModeMapper.findAll();
	}
	
}
