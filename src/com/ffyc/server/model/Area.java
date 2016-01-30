package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AreaMapper;
import com.ffyc.server.mapper.GoodTypeMapper;
import com.ffyc.server.mapper.LogisticMapper;
import com.ffyc.server.mapper.OrderMapper;
import com.ffyc.server.utils.BeanFactory;

public class Area implements Serializable {

	private String id;
	private String logistic;
	private Logistic logisticc;
	private String name;
	private String intro;
	private Integer wfirst;
	private Double wfcost;
	private Integer wappend;
	private Double wacost;
	private String cod;
	private Double premiumrate;
	private String status;
	private List<District> districts;
	private List<Order> orders; 
	
	public static final String STATUS_DEFAULT = "default";

	public Area() {
	}

	public Double getLogicost(Double weight) {
		System.out.println("总重：" + weight);
		System.out.println("首重：" + this.wfirst);
		System.out.println("续重：" + this.wappend);

		Double a = Double
				.valueOf(weight.doubleValue() - this.wfirst.intValue());
		System.out.println("总重减首重：" + a);
		if (a.doubleValue() <= 0.0D) {
			System.out.println("小于等于0：" + this.wfcost);
			return this.wfcost;
		}
		Double b = Double.valueOf(a.doubleValue() / this.wappend.intValue());
		System.out.println("总重－首重）／续重：" + b);
		int c = (int) Math.ceil(b.doubleValue());
		System.out.println("凑整：" + c);
		Double d = Double.valueOf(c * this.wacost.doubleValue()
				+ this.wfcost.doubleValue());
		System.out.println("＊　续重费用 " + this.wacost + " +  首重费用" + this.wfcost
				+ "：" + d);
		return d;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogistic() {
		return logistic;
	}

	public void setLogistic(String logistic) {
		this.logistic = logistic;
	}

	public Logistic getLogisticc() {
		if(StringUtils.isNotEmpty(this.logistic) && this.logisticc ==null){
			LogisticMapper logisticMapper = BeanFactory.getInstance().getLogisticMapper();
			this.logisticc = logisticMapper.findById(logistic);
		}
		return logisticc;
	}

	public void setLogisticc(Logistic logisticc) {
		this.logisticc = logisticc;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getWfirst() {
		return this.wfirst;
	}

	public void setWfirst(Integer wfirst) {
		this.wfirst = wfirst;
	}

	public Double getWfcost() {
		return this.wfcost;
	}

	public void setWfcost(Double wfcost) {
		this.wfcost = wfcost;
	}

	public Integer getWappend() {
		return this.wappend;
	}

	public void setWappend(Integer wappend) {
		this.wappend = wappend;
	}

	public Double getWacost() {
		return this.wacost;
	}

	public void setWacost(Double wacost) {
		this.wacost = wacost;
	}

	public String getCod() {
		return this.cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public Double getPremiumrate() {
		return this.premiumrate;
	}

	public void setPremiumrate(Double premiumrate) {
		this.premiumrate = premiumrate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<District> getDistricts() {
		if(this.districts ==null){
			AreaMapper areaMapper = BeanFactory.getInstance().getAreaMapper();
			this.districts = areaMapper.getDistricts(id);
		}
		return districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	public List<Order> getOrders() {
		if(this.orders == null){
			OrderMapper orderMapper = BeanFactory.getInstance().getOrderMapper();
			Order dc = new Order();
			dc.setArea(id);
			orderMapper.findByOrder(dc, null, null);
		}
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public void insertDistrics() {
		AreaMapper areaMapper = BeanFactory.getInstance()
				.getAreaMapper();
		if(id!=null && this.districts!=null){
			areaMapper.insertDistricts(id, this.districts);
		}
	}
	
	public void deleteDistricts() {
		AreaMapper areaMapper = BeanFactory.getInstance()
				.getAreaMapper();
		if(id!=null){
			areaMapper.deleteDistricts(id);
		}
	}

}
