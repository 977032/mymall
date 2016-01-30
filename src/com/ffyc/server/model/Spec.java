package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.GoodTypeMapper;
import com.ffyc.server.mapper.SpecItemMapper;
import com.ffyc.server.mapper.SpecMapper;
import com.ffyc.server.utils.BeanFactory;

public class Spec implements Serializable {

	private String id;
	private String good;
	private Good goodd;
	private String goodcode;
	private Integer inventory;
	private Double price;
	private Double cprice;
	private Double weight;
	private Integer wgunit;
	private Timestamp ctime;
	private Timestamp utime;
	
	private List<SpecValue> specvalues;

	public Spec() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public Good getGoodd() {
		if(StringUtils.isNotEmpty(this.good) && this.goodd==null){
			GoodMapper goodMapper= BeanFactory.getInstance().getGoodMapper();
			this.goodd= goodMapper.findById(this.good);
		}
		return goodd;
	}

	public void setGoodd(Good goodd) {
		this.goodd = goodd;
	}

	public String getGoodcode() {
		return this.goodcode;
	}

	public void setGoodcode(String goodcode) {
		this.goodcode = goodcode;
	}

	public Integer getInventory() {
		return this.inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Double getPrice() {
		DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
		format.applyPattern("0.00");
		String sprice = format.format(this.price);
		return Double.valueOf(Double.parseDouble(sprice));
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getCprice() {
		DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
		format.applyPattern("0.00");
		try {
			String scprice = format.format(this.cprice);
			return Double.valueOf(Double.parseDouble(scprice));
		} catch (Exception e) {
		}
		return new Double(0.0D);
	}

	public void setCprice(Double cprice) {
		if (cprice != null) {
			this.cprice = cprice;
		} else {
			this.cprice = new Double(0.0D);
		}
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		if (weight != null) {
			this.weight = weight;
		} else {
			this.weight = new Double(0.0D);
		}
		Double w = weight;
		if ((this.wgunit != null) && (this.wgunit.intValue() > 0)) {
			w = Double.valueOf(weight.doubleValue() * this.wgunit.intValue());
		}
		this.weight = w;
	}

	public Integer getWgunit() {
		return this.wgunit;
	}

	public void setWgunit(Integer wgunit) {
		this.wgunit = wgunit;
	}

	public List<SpecValue> getSpecvalues() {
		if(this.specvalues == null){
			SpecMapper specMapper = BeanFactory.getInstance().getSpecMapper();
			this.specvalues = specMapper.getSpecValues(id);
		}
		return this.specvalues;
	}
	public void setSpecvalues(List<SpecValue> specvalues) {
		this.specvalues = specvalues;
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
	
	public void insertSpecValues() {
		SpecMapper specMapper = BeanFactory.getInstance()
				.getSpecMapper();
		if(id!=null && this.specvalues!=null){
			specMapper.insertSpecValues(id, this.specvalues);
		}
	}
	
	public void deleteSpecValues() {
		SpecMapper specMapper = BeanFactory.getInstance()
				.getSpecMapper();
		if(id!=null){
			specMapper.deleteSpecValues(id);
		}
	}
	
}
