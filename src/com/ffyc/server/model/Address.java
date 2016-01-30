package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.DistrictMapper;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.utils.BeanFactory;

public class Address implements Serializable {

	private String id;
	private String member;
	private Member memberr;
	private String district;
	private District districtt;
	private String address;
	private String postcode;
	private String name;
	private String telephone;
	private String mobile;
	private String status;
	private Timestamp ctime;
	private Timestamp utime;
	public final static String STATUS_NORMAL = "normal";
	
	private Set orderses = new HashSet(0);

	public Address() {
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
		if(StringUtils.isNotEmpty(this.member) && this.memberr==null){
			MemberMapper memberMapper = BeanFactory.getInstance().getMemberMapper();
			this.memberr = memberMapper.findById(this.member);
		}
		return memberr;
	}

	public void setMemberr(Member memberr) {
		this.memberr = memberr;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public District getDistrictt() {
		if(StringUtils.isNotEmpty(this.district) && this.districtt == null){
			DistrictMapper districtMapper = BeanFactory.getInstance().getDistrictMapper();
			this.districtt = districtMapper.findById(Integer.valueOf(this.district));
		}
		return districtt;
	}

	public void setDistrictt(District districtt) {
		this.districtt = districtt;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		if (status != null) {
			this.status = status;
		} else {
			this.status = STATUS_NORMAL;
		}
	}

	public Set getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set orderses) {
		this.orderses = orderses;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getUtime() {
		return utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}
	
	
}
