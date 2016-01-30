package com.ffyc.server.form;

public class ThirdPartRegistForm {

	private String name;
	
    private String gender;
    
    private String platform;
    
    public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}


	private String pId;
    
    

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}


	public String getpId() {
		return pId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


	public void setpId(String pId) {
		this.pId = pId;
	}
}
