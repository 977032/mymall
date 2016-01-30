package com.ffyc.server.mapper;

import org.apache.ibatis.annotations.Param;

public interface ThirdPartMapper {
	
	public String getUserId(@Param("platform") String platform,@Param("pId") String pId);
	
	public void insertThirdPartUser(@Param("userId") String userId,@Param("platform") String platform,@Param("pId") String pId);
}
