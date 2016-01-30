package com.ffyc.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.service.AppService;
import com.ffyc.server.mapper.AppMapper;


@Service("appService")
public class AppServiceImpl implements AppService {

	@Autowired
	private AppMapper appMapper;
	

	public String getUserContract() {
		// TODO Auto-generated method stub
		return this.appMapper.getUserContract();
    	
	}

}
