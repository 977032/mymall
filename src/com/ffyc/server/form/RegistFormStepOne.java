package com.ffyc.server.form;

public class RegistFormStepOne {

	private String name;
	
	private String mobile;

    private String password;

    private String verifyCode;
    
    private String gender;

	public String getMobile() {
		return mobile;
	}

	public String getPassword() {
		return password;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
