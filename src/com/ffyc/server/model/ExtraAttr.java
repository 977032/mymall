package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import sun.nio.cs.ext.TIS_620;

import com.ffyc.server.mapper.ExtraAttrMapper;
import com.ffyc.server.mapper.ExtraAttrValueMapper;
import com.ffyc.server.utils.BeanFactory;

public class ExtraAttr implements Serializable {

	private String id;
	private String name;
	private String alias;
	private String viewtype;
	private String display;
	private Integer csort;
	private List<ExtraAttrValue> extraattrvalues;
	private List<GoodType> goodtypes;

	public ExtraAttr() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getViewtype() {
		return viewtype;
	}

	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}

	public String getDisplay() {
		return this.display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public List<ExtraAttrValue> getExtraattrvalues() {
		if(this.extraattrvalues == null){
			ExtraAttrValueMapper extraAttrValueMapper = BeanFactory.getInstance().getExtraAttrValueMapper();
			ExtraAttrValue dc = new ExtraAttrValue();
			dc.setExtraattr(id);
			this.extraattrvalues = extraAttrValueMapper.findByExtraAttrValue(dc, null, null);
		}
		return extraattrvalues;
	}

	public void setExtraattrvalues(List<ExtraAttrValue> extraattrvalues) {
		this.extraattrvalues = extraattrvalues;
	}

	public List<GoodType> getGoodtypes() {
		if(this.goodtypes== null){
			ExtraAttrMapper extraAttrMapper = BeanFactory.getInstance().getExtraAttrMapper();
			this.goodtypes = extraAttrMapper.getGoodTypes(id);
		}
		return goodtypes;
	}

	public void setGoodtypes(List<GoodType> goodtypes) {
		this.goodtypes = goodtypes;
	}
}
