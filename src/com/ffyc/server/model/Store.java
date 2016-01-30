package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Store implements Serializable {
	private static final long serialVersionUID = 2482327322520730845L;
	private String id;
	private String name;
	private String status;
	private float  lng;
	private float  lat;
	private String manager;
	private String remark;
	private String region;
	private Timestamp ctime;
	private Timestamp utime;

	public Store() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getLng() {
		return lng;
	}

	public void setLng(float lng) {
		this.lng = lng;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getUtime() {
		return utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	
}
