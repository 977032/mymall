package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.dc.PaySettingDC;
import com.ffyc.server.mapper.PaySettingMapper;
import com.ffyc.server.model.PaySetting;
import com.ffyc.server.service.PaySettingService;

@Service("paySettingService")
public class PaySettingServiceImpl extends BaseServiceImpl implements PaySettingService {


    @Autowired
    private PaySettingMapper paySettingMapper;
	
	@Override
	public void save(PaySetting paysetting) {
		paySettingMapper.save(paysetting);
	}
	
	@Override
	public void update(PaySetting paysetting) {
		paySettingMapper.update(paysetting);
	}

	@Override
	public void delete(String id) {
		paySettingMapper.delete(id);
	}

	@Override
	public PaySetting findById(String id) {
		return paySettingMapper.findById(id);
	}

	@Override
	public PaySetting findByProperty(String property) {
		return paySettingMapper.findByProperty(property);
	}

	@Override
	public List<PaySetting> findAll() {
		return paySettingMapper.findAll();
	}

	@Override
	public List<PaySetting> findByPaySetting(PaySettingDC paysetting) {
		return paySettingMapper.findByPaySetting(paysetting);
	}
	
	@Override
	public List<PaySetting> findAllRoot(String pmtype) {
		return paySettingMapper.findAllRoot(pmtype);
	}
	
	
}
