package com.ffyc.server.vo;

import java.sql.Timestamp;

import com.ffyc.server.model.MessageBasic;


public class MessageVO extends MessageBasic {
	private String id;
	private Timestamp createTime;
	private String fromId;
	private String fromName;
	private String toName;
	
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getId() {
		return id;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
