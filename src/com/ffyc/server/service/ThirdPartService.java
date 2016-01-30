package com.ffyc.server.service;


public interface ThirdPartService {
	
	public String getUserId(String platform,String pId);

	public void insertThirdPartUser(String userId, String platform, String pId);
	
	
}
