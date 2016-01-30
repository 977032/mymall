package com.ffyc.server.model;

public class MessageBasic {
	
	private String content;
	private String toId;
	public String getContent() {
		return content;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
