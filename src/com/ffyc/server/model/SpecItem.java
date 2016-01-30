package com.ffyc.server.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.ffyc.server.mapper.SpecItemMapper;
import com.ffyc.server.mapper.SpecValueMapper;
import com.ffyc.server.utils.BeanFactory;

public class SpecItem implements Serializable {
	private String id;
	private String name;
	private String remark;
	private String dtype;
	private String dstyle;
	private List<SpecValue> specvalues;
	private List<GoodType> goodtypes;

	public SpecItem() {
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDtype() {
		return this.dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}

	public String getDstyle() {
		return this.dstyle;
	}

	public void setDstyle(String dstyle) {
		this.dstyle = dstyle;
	}

	public List<SpecValue> getSpecvalues() {
		if (this.specvalues == null) {
			SpecValueMapper specValueMapper = BeanFactory.getInstance().getSpecValueMapper();
			SpecValue dc = new SpecValue();
			dc.setSpecitem(id);
			this.specvalues = specValueMapper.findBySpecValue(dc, null, null);
		}
		return this.specvalues;
	}

	public Set getSpecvalues(boolean sort) {
		TreeSet<SpecValue> treeset = new TreeSet(new Comparator() {
			public int compare(Object a, Object b) {
				return ((SpecValue) a).getName().compareTo(
						((SpecValue) b).getName());
			}
		});
		treeset.addAll(this.specvalues);
		return treeset;
	}

	public void setSpecvalues(List<SpecValue> specvalues) {
		this.specvalues = specvalues;
	}

	public List<GoodType> getGoodtypes() {
		if(this.goodtypes == null){
			SpecItemMapper specItemMapper = BeanFactory.getInstance().getSpecItemMapper();
			this.goodtypes = specItemMapper.getGoodTypes(id);
		}
		return this.goodtypes;
	}

	public void setGoodtypes(List<GoodType> goodtypes) {
		this.goodtypes = goodtypes;
	}
}
