package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AddressMapper;
import com.ffyc.server.mapper.AreaMapper;
import com.ffyc.server.mapper.EvaluateMapper;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.OrderItemMapper;
import com.ffyc.server.mapper.PayModeMapper;
import com.ffyc.server.utils.BeanFactory;

public class Order implements Serializable {

	private String id;
	private String member;
	private Member memberr;
	private String paymode;
	private PayMode paymodee;
	private String address;
	private Address addresss;
	private String area;
	private Area areaa;
	private String code;
	private String subject;
	private String body;
	private String status;
	private String status_brief;
	private Timestamp ctime;
	private Timestamp utime;
	private Double amount;
	private Double logicost;
	private Double paycost;
	private Double baojia;
	private Double weight;
	private String invoicetitle;
	private Double duty;
	private Double discount;
	private Double actamount;
	private List<Evaluate> evaluates;
	private List<OrderItem> orderitems;
	private Integer cartnumber;
	private Double cartamount;
	
	public final static String STATUS_TRADE_CLOSED = "TRADE_CLOSED";
	public final static String STATUS_TRADE_FINISHED = "TRADE_FINISHED";
	public final static String STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	public final static String STATUS_WAIT_SELLER_SEND_GOODS = "WAIT_SELLER_SEND_GOODS";
	public final static String STATUS_WAIT_BUYER_CONFIRM_GOODS= "WAIT_BUYER_CONFIRM_GOODS";
	
	public Order() {
	}

	public Integer getCartnumber() {
		this.cartnumber = Integer.valueOf(0);
		Iterator it = getOrderitems().iterator();
		while (it.hasNext()) {
			OrderItem oi = (OrderItem) it.next();
			this.cartnumber = Integer.valueOf(this.cartnumber.intValue() + 1);
		}
		return this.cartnumber;
	}

	public void setCartnumber(Integer cartnumber) {
		this.cartnumber = cartnumber;
	}

	public Double getCartamount() {
		this.cartamount = new Double(0.0D);
		Iterator it = getOrderitems().iterator();
		while (it.hasNext()) {
			OrderItem oi = (OrderItem) it.next();
			this.cartamount = Double.valueOf(this.cartamount.doubleValue()
					+ oi.getSubtotal().doubleValue());
		}
		return this.cartamount;
	}

	public void setCartamount(Double cartAmount) {
		this.cartamount = cartAmount;
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

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public PayMode getPaymodee() {
		if(StringUtils.isNotEmpty(this.paymode) && this.paymodee == null){
			PayModeMapper payModeMapper = BeanFactory.getInstance().getPayModeMapper();
			this.paymodee = payModeMapper.findById(this.paymode);
		}
		return paymodee;
	}

	public void setPaymodee(PayMode paymodee) {
		this.paymodee = paymodee;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Address getAddresss() {
		if(StringUtils.isNotEmpty(this.address) && this.addresss ==null){
			AddressMapper addressMapper = BeanFactory.getInstance().getAddressMapper();
			this.addresss = addressMapper.findById(this.address);
		}
		return addresss;
	}

	public void setAddresss(Address addresss) {
		this.addresss = addresss;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Area getAreaa() {
		if(StringUtils.isNotEmpty(this.area) && this.areaa == null){
			AreaMapper areaMapper = BeanFactory.getInstance().getAreaMapper();
			this.areaa = areaMapper.findById(this.area);
		}
		return areaa;
	}

	public void setAreaa(Area areaa) {
		this.areaa = areaa;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		if (status.equals(STATUS_WAIT_BUYER_PAY)) {
			this.status_brief = "等待买家付款";
		}
		if (status.equals(STATUS_WAIT_SELLER_SEND_GOODS)) {
			this.status_brief = "买家已付款";
		}
		if (status.equals(STATUS_WAIT_BUYER_CONFIRM_GOODS)) {
			this.status_brief = "卖家已发货";
		}
		if (status.equals(STATUS_TRADE_FINISHED)) {
			this.status_brief = "交易完成";
		}
		if (status.equals(STATUS_TRADE_CLOSED)) {
			this.status_brief = "交易关闭";
		}
		this.status = status;
	}

	public String getStatus_brief() {
		return this.status_brief;
	}

	public void setStatus_brief(String statusBrief) {
		this.status_brief = statusBrief;
	}

	public Timestamp getCtime() {
		return this.ctime;
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

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getLogicost() {
		return this.logicost;
	}

	public void setLogicost(Double logicost) {
		this.logicost = logicost;
	}

	public Double getPaycost() {
		return this.paycost;
	}

	public void setPaycost(Double paycost) {
		this.paycost = paycost;
	}

	public Double getBaojia() {
		return this.baojia;
	}

	public void setBaojia(Double baojia) {
		this.baojia = baojia;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getInvoicetitle() {
		return this.invoicetitle;
	}

	public void setInvoicetitle(String invoicetitle) {
		this.invoicetitle = invoicetitle;
	}

	public Double getDuty() {
		return this.duty;
	}

	public void setDuty(Double duty) {
		this.duty = duty;
	}

	public Double getDiscount() {
		return this.discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getActamount() {
		return this.actamount;
	}

	public void setActamount(Double actamount) {
		this.actamount = actamount;
	}

	public List<Evaluate> getEvaluates() {
		if(this.evaluates == null){
			EvaluateMapper evaluateMapper = BeanFactory.getInstance().getEvaluateMapper();
			Evaluate dc =new Evaluate();
			dc.setOrder(id);
			this.evaluates = evaluateMapper.findByEvaluate(dc, null, null);
		}
		return evaluates;
	}

	public void setEvaluates(List<Evaluate> evaluates) {
		this.evaluates = evaluates;
	}

	public List<OrderItem> getOrderitems() {
		if(this.orderitems == null){
			if(this.id!=null){
				OrderItemMapper orderItemMapper = BeanFactory.getInstance().getOrderItemMapper();
				OrderItem dc = new OrderItem();
				dc.setOrder(id);
				this.orderitems = orderItemMapper.findByOrderItem(dc, null, null);
			}else{
				this.orderitems = new ArrayList<OrderItem>();
			}
		}
		return orderitems;
	}

	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}
	
}
