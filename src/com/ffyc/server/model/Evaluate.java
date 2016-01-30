package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.OrderMapper;
import com.ffyc.server.utils.BeanFactory;

public class Evaluate implements Serializable {

	private String id;
	private String member;
	private Member memberr;
	private String order;
	private Order orderr;
	private String etype;
	private Integer positive;
	private Integer neutral;
	private Integer negative;
	private Timestamp ctime;

	public Evaluate() {
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

	public String getOrder() {
		if(StringUtils.isNotEmpty(this.order) && this.orderr == null){
			OrderMapper orderMapper = BeanFactory.getInstance().getOrderMapper();
			this.orderr = orderMapper.findById(this.order);
		}
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Order getOrderr() {
		return orderr;
	}

	public void setOrderr(Order orderr) {
		this.orderr = orderr;
	}

	public String getEtype() {
		return this.etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public Integer getPositive() {
		return this.positive;
	}

	public void setPositive(Integer positive) {
		this.positive = positive;
	}

	public Integer getNeutral() {
		return this.neutral;
	}

	public void setNeutral(Integer neutral) {
		this.neutral = neutral;
	}

	public Integer getNegative() {
		return this.negative;
	}

	public void setNegative(Integer negative) {
		this.negative = negative;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}
