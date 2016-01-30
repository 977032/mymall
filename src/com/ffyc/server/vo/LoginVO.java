package com.ffyc.server.vo;

import java.io.Serializable;

import com.ffyc.server.model.User;

public class LoginVO implements Serializable
{
    private static final long serialVersionUID = 3032347707244636016L;

    private String id;
    private String account;
    private String name;
    private String mobile;
    private String token;
    private int type;

    
    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }
    
	public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

    public boolean isManager(){
    	return this.type == User.MANAGER_TYPE;
    }
    
    public boolean isMember(){
    	return this.type == User.MEMBER_TYPE;
    }
}
