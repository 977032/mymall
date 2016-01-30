package com.ffyc.server.model;

import java.io.Serializable;

public class PaySetting implements Serializable {

	private String id;
	private String paymode;
	private PayMode paymodee;
	private String name;
	private String property;
	private String value;
	private String pmtype;

	public PaySetting() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PayMode getPaymodee() {
		return paymodee;
	}

	public void setPaymodee(PayMode paymodee) {
		this.paymodee = paymodee;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getPmtype() {
		return pmtype;
	}

	public void setPmtype(String pmtype) {
		this.pmtype = pmtype;
	}
	
}
