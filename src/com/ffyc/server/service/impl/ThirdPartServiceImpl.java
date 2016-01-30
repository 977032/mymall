package com.ffyc.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.ThirdPartMapper;
import com.ffyc.server.service.ThirdPartService;

@Service("thirdPartService")
public class ThirdPartServiceImpl implements ThirdPartService {

	@Autowired
	private ThirdPartMapper thirdPartMapper;
	
	@Override
	public String getUserId(String platform, String pId) {
		return this.thirdPartMapper.getUserId(platform, pId);
	}
	
	@Override
	public void insertThirdPartUser(String userId,String platform,String pId){
		this.thirdPartMapper.insertThirdPartUser(userId, platform, pId);
	}

}
