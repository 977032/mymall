package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.ExtraAttrMapper;
import com.ffyc.server.mapper.ExtraAttrValueMapper;
import com.ffyc.server.utils.BeanFactory;

public class ExtraAttrValue implements Serializable {

	private String id;
	private String extraattr;
	private ExtraAttr extraattrr;
	private String value;
	private Integer csort;
	private List<Good> goods;

	public ExtraAttrValue() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExtraattr() {
		
		return extraattr;
	}

	public void setExtraattr(String extraattr) {
		this.extraattr = extraattr;
	}

	public ExtraAttr getExtraattrr() {
		if(StringUtils.isNotEmpty(this.extraattr) && this.extraattrr==null){
			ExtraAttrMapper extraAttrMapper = BeanFactory.getInstance().getExtraAttrMapper();
			this.extraattrr = extraAttrMapper.findById(this.extraattr);
		}
		return extraattrr;
	}

	public void setExtraattrr(ExtraAttr extraattrr) {
		this.extraattrr = extraattrr;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public List<Good> getGoods() {
		if(this.goods ==null){
			ExtraAttrValueMapper extraAttrValueMapper = BeanFactory.getInstance().getExtraAttrValueMapper();
			this.goods = extraAttrValueMapper.getGoods(id);
		}
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

}
