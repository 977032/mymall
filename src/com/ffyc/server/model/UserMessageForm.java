package com.ffyc.server.model;

public class UserMessageForm {
	private String toId;
	private String content;
	public String getToId() {
		return toId;
	}
	public String getContent() {
		return content;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
