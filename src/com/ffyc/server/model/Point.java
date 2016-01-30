package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import com.ffyc.server.mapper.PointMapper;
import com.ffyc.server.mapper.PointRunAccountMapper;
import com.ffyc.server.utils.BeanFactory;

public class Point implements Serializable {

	private String id;
	private String account;
	private String status;
	private Integer balance;
	private String remarks;
	private Timestamp ctime;
	private List<PointRunAccount> prunaccs;
	private List<Member> members;
	
	public final static String STATUS_NORMAL = "normal";

	public Point() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBalance() {
		return this.balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public List<PointRunAccount> getPrunaccs() {
		if(this.prunaccs == null){
			PointRunAccountMapper pointRunAccountMapper = BeanFactory.getInstance().getPointRunAccountMapper();
			PointRunAccount dc = new PointRunAccount();
			dc.setPoint(id);
			this.prunaccs = pointRunAccountMapper.findByPointRunAccount(dc, null, null);
		}
		return prunaccs;
	}

	public void setPrunaccs(List<PointRunAccount> prunaccs) {
		this.prunaccs = prunaccs;
	}

	public List<Member> getMembers() {
		if(this.members == null){
			PointMapper pointMapper = BeanFactory.getInstance().getPointMapper();
			this.members = pointMapper.getMembers(id);
		}
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}

}
