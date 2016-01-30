package com.ffyc.server.model;

import java.sql.Timestamp;
import java.util.Date;

public interface User {
	public final int MANAGER_TYPE = 0;
	public final int MEMBER_TYPE = 1;

	public String getId();

	public void setId(String id);

	public String getName();

	public void setName(String name);

	public String getAccount();

	public void setAccount(String account);

	public String getPassword();

	public void setPassword(String password);

	public String getTelephone();

	public void setTelephone(String telephone);

	public String getMobile();

	public void setMobile(String mobile);

	public String getGender();

	public void setGender(String gender);

	public Timestamp getCtime();

	public void setCtime(Timestamp ctime);

	public Timestamp getUtime();

	public void setUtime(Timestamp utime);

	public boolean isManager();

	public boolean isMember();

}