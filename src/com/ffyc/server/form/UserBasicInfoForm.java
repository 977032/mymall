package com.ffyc.server.form;


public class UserBasicInfoForm {
	
	private String birthday;
	private String gender;
	private String industry;
	private String username;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getBirthday() {
		return birthday;
	}
	public String getGender() {
		return gender;
	}
	public String getIndustry() {
		return industry;
	}
	public void setBirthDay(String birthday) {
		this.birthday = birthday;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}

}
