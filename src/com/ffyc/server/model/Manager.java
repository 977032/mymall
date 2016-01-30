package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Manager implements User, Serializable {
	private static final long serialVersionUID = 48843681755857295L;
	public static final String STATUS_NORMAL = "normal";
	public static final String STATUS_DELETE = "delete";
	public static final String CTYPE_SUPERADMIN = "superadmin";
	public static final String CTYPE_NORMAL = "normal";

	private String id;
	private String account;
	private String name;
	private String password;
	private String telephone;
	private String mobile;

	private String gender;
	private Timestamp ctime;
	private Timestamp utime;
	private String mgroup;
	private String image;
	private String status;
	private String email;
	private String ctype;
	private String nickname;
	private String idcard;
	private Timestamp lastlogin;
	private Integer logincount;
	private String cpassword;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getUtime() {
		return this.utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	@Override
	public boolean isManager() {
		return true;
	}

	@Override
	public boolean isMember() {
		return false;
	}

	public String getStatus() {
		return this.status;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getMgroup() {
		return mgroup;
	}

	public void setMgroup(String mgroup) {
		this.mgroup = mgroup;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCtype() {
		return this.ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Timestamp getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Integer getLogincount() {
		return this.logincount;
	}

	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}

}