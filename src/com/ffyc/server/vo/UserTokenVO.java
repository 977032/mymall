package com.ffyc.server.vo;

import java.sql.Timestamp;

public class UserTokenVO {
	private String userId;
	private String token;
	private Timestamp lastUpdateTime;
	
	
	public String getUserId() {
		return userId;
	}
	public String getToken() {
		return token;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
