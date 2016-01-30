package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.GoodType;

public class GoodCreateAction extends BaseAction {
	private static final long serialVersionUID = -1790111470325582697L;
	private List localelist;
	private List catelist;
	private String cg;
	private Category category;
	private List goodtypelist;
	private String gt;
	private GoodType goodtype;
	private Good good = new Good();
	private List<ExtraAttrValue> extrattrvalues;

	public String execute() throws Exception {
		this.localelist = this.localeService.findAll();
		this.catelist = this.categoryService.findAllTree();
		this.goodtypelist = this.goodTypeService.findAll();
		if (StringUtils.isNotEmpty(this.cg)) {
			this.category = this.categoryService.findById(this.cg);
			if (StringUtils.isNotEmpty(this.gt)) {
				this.goodtype = this.goodTypeService.findById(this.gt);
			} else {
				this.goodtype = this.category.getGoodtypee();
			}
			if(this.category.getGoodtypee()!=null){
				this.category.getGoodtypee().getBrands();
			}
		}
		return "success";
	}

	public String create() throws Exception {
		this.good.setId(getUuid());
		this.good.setCtime(getTimestamp());
		this.good.setVistor(Integer.valueOf(0));
		this.good.setStatus(Good.STATUS_NORMAL);
		this.good.setManager(getManagerFromSession().getId());
		if(this.good.getBrandd() !=null){
			this.good.setBrand(this.good.getBrandd().getId());
		}
		if(this.good.getLocalee() == null){
			this.good.setLocale("zh_CN");
		}else{
			this.good.setLocale(this.good.getLocalee().getId());
		}
		if(this.good.getCategoryy()!=null){
			this.good.setCategory(this.good.getCategoryy().getId());
		}
		if (this.good.getGoodtypee() == null) {
			this.good.setGoodtype(null);
		}else{
			this.good.setGoodtype(this.good.getGoodtypee().getId());
		}
		if ((this.extrattrvalues != null) && (this.extrattrvalues.size() > 0)) {
			for (int i = 0; i < this.extrattrvalues.size(); i++) {
				ExtraAttrValue evae = (ExtraAttrValue) this.extrattrvalues.get(i);
				if ((evae != null) && (evae.getId() != null)
						&& (!evae.getId().equals(""))) {
					this.good.getExtraattrvalues().add(evae);
				}
			}
		}
		this.goodService.save(this.good);
		if(this.good.getExtraattrvalues().size()>0){
			this.good.insertExtraAttrValues();
		}
		return "success";
	}

	public List getLocalelist() {
		return this.localelist;
	}

	public void setLocalelist(List localelist) {
		this.localelist = localelist;
	}

	public List getCatelist() {
		return this.catelist;
	}

	public void setCatelist(List catelist) {
		this.catelist = catelist;
	}

	public String getCg() {
		return this.cg;
	}

	public void setCg(String cg) {
		this.cg = cg;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List getGoodtypelist() {
		return this.goodtypelist;
	}

	public void setGoodtypelist(List goodtypelist) {
		this.goodtypelist = goodtypelist;
	}

	public String getGt() {
		return this.gt;
	}

	public void setGt(String gt) {
		this.gt = gt;
	}

	public GoodType getGoodtype() {
		return this.goodtype;
	}

	public void setGoodtype(GoodType goodtype) {
		this.goodtype = goodtype;
	}

	public Good getGood() {
		return this.good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public List<ExtraAttrValue> getExtrattrvalues() {
		return extrattrvalues;
	}

	public void setExtrattrvalues(List<ExtraAttrValue> extrattrvalues) {
		this.extrattrvalues = extrattrvalues;
	}
}
