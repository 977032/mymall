package com.ffyc.server.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.context.support.StaticApplicationContext;

import com.ffyc.server.service.BaseService;

public class BaseServiceImpl implements BaseService {

	protected Map session; 

	public Map getSession() {
		return this.session;
	}

	public void setSession(Map session) {
		this.session = session;
	}

	public String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

}
