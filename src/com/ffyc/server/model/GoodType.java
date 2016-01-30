package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.ffyc.server.mapper.CategoryMapper;
import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.GoodTypeMapper;
import com.ffyc.server.utils.BeanFactory;

public class GoodType implements Serializable {
	private String id;
	private String name;
	private String alias;
	private List<Brand> brands;
	private List<SpecItem> specitems;
	private List<Category> categories;
	private List<Good> goods;
	private List<ExtraAttr> extraattrs;

	public GoodType() {
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

	public List<Brand> getBrands() {
		if (this.brands == null) {
			GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
					.getGoodTypeMapper();
			this.brands = goodTypeMapper.getBrands(id);
		}
		return this.brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public List<SpecItem> getSpecitems() {
		if (this.specitems == null) {
			GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
					.getGoodTypeMapper();
			this.specitems = goodTypeMapper.getSpecItems(id);
		}
		return this.specitems;
	}

	public void setSpecitems(List<SpecItem> specitems) {
		this.specitems = specitems;
	}

	public List<Category> getCategories() {
		if(this.categories == null){
			CategoryMapper categoryMapper = BeanFactory.getInstance().getCategoryMapper();
			Category dc = new Category();
			dc.setGoodtype(id);
			this.categories = categoryMapper.findByCategory(dc, null, null);
		}
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Good> getGoods() {
		if(this.goods == null){
			GoodMapper goodMapper = BeanFactory.getInstance().getGoodMapper();
			Good dc =  new Good();
			dc.setGoodtype(id);
			this.goods = goodMapper.findByGood(dc, null, null);
		}
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public List<ExtraAttr> getExtraattrs() {
		if (this.extraattrs == null) {
			GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
					.getGoodTypeMapper();
			this.extraattrs = goodTypeMapper.getExtraAttrs(id);
		}
		return this.extraattrs;
	}

	public void setExtraattrs(List<ExtraAttr> extraattrs) {
		this.extraattrs = extraattrs;
	}

	public void insertBrands() {
		GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
				.getGoodTypeMapper();
		if(id!=null && this.brands!=null){
			goodTypeMapper.insertBrands(id, this.brands);
		}
	}

	public void insertSpecItems() {
		GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
				.getGoodTypeMapper();
		if(id!=null && this.specitems!=null){
			goodTypeMapper.insertSpecItems(id, this.specitems);
		}
	}

	public void insertExtraAttrs() {
		GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
				.getGoodTypeMapper();
		if(id!=null && this.extraattrs!=null){
			goodTypeMapper.insertExtraAttrs(id, this.extraattrs);
		}
	}

	public void deleteBrands() {
		GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
				.getGoodTypeMapper();
		if(id!=null){
			goodTypeMapper.deleteBrands(id);
		}
	}

	public void deleteSpecItems() {
		GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
				.getGoodTypeMapper();
		if(id!=null){
			goodTypeMapper.deleteSpecItems(id);
		}
	}

	public void deleteExtraAttrs() {
		GoodTypeMapper goodTypeMapper = BeanFactory.getInstance()
				.getGoodTypeMapper();
		if(id!=null){
			goodTypeMapper.deleteExtraAttrs(id);
		}
	}
}
