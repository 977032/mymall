package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.PointMapper;
import com.ffyc.server.utils.BeanFactory;

public class PointRunAccount implements Serializable {

	private String id;
	private String member;
	private Member memberr;
	private String manager;
	private Manager managerr;
	private String point;
	private Point pointt;
	private String status;
	private Integer balance;
	private Integer getout;
	private Integer payin;
	private String tabloid;
	private String remarks;
	private Timestamp ctime;

	public PointRunAccount() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Member getMemberr() {
		if(StringUtils.isNotEmpty(this.member) && this.memberr == null){
			MemberMapper memberMapper = BeanFactory.getInstance().getMemberMapper();
			this.memberr = memberMapper.findById(this.member);
		}
		return memberr;
	}

	public void setMemberr(Member memberr) {
		this.memberr = memberr;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Manager getManagerr() {
		if(StringUtils.isNotEmpty(this.manager) && this.manager == null){
			ManagerMapper managerMapper = BeanFactory.getInstance().getManagerMapper();
			this.managerr = managerMapper.findById(this.manager);
		}
		return managerr;
	}

	public void setManagerr(Manager managerr) {
		this.managerr = managerr;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public Point getPointt() {
		if(StringUtils.isNotEmpty(this.point) && this.pointt == null){
			PointMapper pointMapper = BeanFactory.getInstance().getPointMapper();
			this.pointt = pointMapper.findById(this.point);
		}
		return pointt;
	}

	public void setPointt(Point pointt) {
		this.pointt = pointt;
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

	public Integer getGetout() {
		return this.getout;
	}

	public void setGetout(Integer getout) {
		this.getout = getout;
	}

	public Integer getPayin() {
		return this.payin;
	}

	public void setPayin(Integer payin) {
		this.payin = payin;
	}

	public String getTabloid() {
		return this.tabloid;
	}

	public void setTabloid(String tabloid) {
		this.tabloid = tabloid;
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
}
