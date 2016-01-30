package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.OrderMapper;
import com.ffyc.server.mapper.SpecMapper;
import com.ffyc.server.utils.BeanFactory;

public class OrderItem implements Serializable {

	private String id;
	private String member;
	private Member memberr;
	private String order;
	private Order orderr;
	private String good;
	private Good goodd;
	private String spec;
	private Spec specc;
	private Double unitprice;
	private Integer number;
	private Double subtotal;
	private Double actsum;
	private Double discount;
	private String ispay;
	private String status;
	private Timestamp paytime;
	private Timestamp exptime;
	private Timestamp utime;
	private Timestamp ctime;
	
	public final static String STATUS_NORMAL = "normal";

	public OrderItem() {
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
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Order getOrderr() {
		if(StringUtils.isNotEmpty(this.order) && this.orderr == null){
			OrderMapper orderMapper = BeanFactory.getInstance().getOrderMapper();
			this.orderr = orderMapper.findById(this.order);
		}
		return orderr;
	}

	public void setOrderr(Order orderr) {
		this.orderr = orderr;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public Good getGoodd() {
		if(StringUtils.isNotEmpty(this.good) && this.goodd == null){
			GoodMapper goodMapper = BeanFactory.getInstance().getGoodMapper();
			this.goodd = goodMapper.findById(this.good);
		}
		return goodd;
	}

	public void setGoodd(Good goodd) {
		this.goodd = goodd;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Spec getSpecc() {
		if(StringUtils.isNotEmpty(this.spec) && this.specc!=null){
			SpecMapper specMapper = BeanFactory.getInstance().getSpecMapper();
			this.specc = specMapper.findById(this.spec);
		}
		return specc;
	}

	public void setSpecc(Spec specc) {
		this.specc = specc;
	}

	public Double getUnitprice() {
		return this.unitprice;
	}

	public void setUnitprice(Double unitprice) {
		this.unitprice = unitprice;
	}

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getActsum() {
		return this.actsum;
	}

	public void setActsum(Double actsum) {
		this.actsum = actsum;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getIspay() {
		return this.ispay;
	}

	public void setIspay(String ispay) {
		this.ispay = ispay;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getPaytime() {
		return this.paytime;
	}

	public void setPaytime(Timestamp paytime) {
		this.paytime = paytime;
	}

	public Timestamp getExptime() {
		return this.exptime;
	}

	public void setExptime(Timestamp exptime) {
		this.exptime = exptime;
	}

	public Timestamp getUtime() {
		return this.utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}
