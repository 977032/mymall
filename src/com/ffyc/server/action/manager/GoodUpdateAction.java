package com.ffyc.server.action.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.model.Category;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.GoodType;

public class GoodUpdateAction extends BaseAction {
	private static final long serialVersionUID = -416415424928530659L;
	private List localelist;
	private List catelist;
	private String cg;
	private Category ccategory;
	private List goodtypelist;
	private String gt;
	private GoodType goodtype;
	private String gid;
	private Good good;
	private List<ExtraAttrValue> extraattrvalues;
	private String[] keywords;

	public String execute() throws Exception {
		this.localelist = this.localeService.findAll();
		this.catelist = this.categoryService.findAllTree();
		this.goodtypelist = this.goodTypeService.findAll();
		this.good = this.goodService.findById(this.gid);
		if (StringUtils.isNotEmpty(this.cg)) {
			this.ccategory = this.categoryService.findById(this.cg);
			if (StringUtils.isNotEmpty(this.gt)) {
				this.goodtype = this.goodTypeService.findById(this.gt);
			} else {
				this.goodtype = this.ccategory.getGoodtypee();
			}
		} else {
			this.ccategory = this.good.getCategoryy();
			this.goodtype = this.good.getGoodtypee();
		}
		if(this.ccategory!=null){
			if(this.ccategory.getGoodtypee()!=null){
				this.ccategory.getGoodtypee().getBrands();
			}
		}
		this.good.getLocalee();
		this.good.getBrandd();
		this.good.getGoodtypee();
		this.goodtype.getExtraattrs();
		if(this.goodtype.getExtraattrs()!=null){
			Iterator it = this.goodtype.getExtraattrs().iterator();
			while(it.hasNext()){
				ExtraAttr extraAttr = (ExtraAttr)it.next();
				extraAttr.getExtraattrvalues();
			}
		}
		
		this.good.getExtraattrvalues();
		
		return "success";
	}

	public String update() throws Exception {
		Good ug = this.goodService.findById(this.good.getId());
		ug.setBrand(this.good.getBrandd().getId());
		ug.setCategory(this.good.getCategoryy().getId());
		ug.setCsort(this.good.getCsort());
		ug.setDetail(this.good.getDetail());
		ug.setGoodtype(this.good.getGoodtypee().getId());
		ug.setIntro(this.good.getIntro());
		ug.setInventory(this.good.getInventory());
		ug.setKeyword(this.good.getKeyword());
		ug.setLocale(this.good.getLocalee().getId());
		ug.setManager(getManagerFromSession().getId());
		ug.setMkeyword(this.good.getMkeyword());
		ug.setMdescription(this.good.getMdescription());
		ug.setName(this.good.getName());
		ug.setSn(this.good.getSn());
		ug.setStatus(this.good.getStatus());
		ug.setPoints(this.good.getPoints());
		ug.setPrice(this.good.getPrice());
		ug.setMprice(this.good.getMprice());
		ug.setPromote(this.good.getPromote());
		ug.setUtime(getTimestamp());
		ug.setWeight(this.good.getWeight());
		ug.setExtraattrvalues(new ArrayList());
		if ((this.extraattrvalues != null) && (this.extraattrvalues.size() > 0)) {
			for (int i = 0; i < this.extraattrvalues.size(); i++) {
				ExtraAttrValue evae = (ExtraAttrValue) this.extraattrvalues.get(i);
				if ((evae != null) && (evae.getId() != null)
						&& (!evae.getId().equals(""))) {
					ug.getExtraattrvalues().add(evae);
				}
			}
		}
		if (this.keywords != null) {
			String keyword = "";
			for (int i = 0; i < this.keywords.length; i++) {
				keyword = keyword + this.keywords[i] + ",";
			}
			ug.setKeyword(keyword);
		}
		this.goodService.update(ug);
		ug.deleteExtraAttrValues();
		ug.insertExtraAttrValues();
		setGid(ug.getId());
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

	public Category getCcategory() {
		return this.ccategory;
	}

	public void setCcategory(Category ccategory) {
		this.ccategory = ccategory;
	}

	public List getGoodtypelist() {
		return goodtypelist;
	}

	public void setGoodtypelist(List goodtypelist) {
		this.goodtypelist = goodtypelist;
	}

	public String getGt() {
		return this.gt;
	}

	public void setGe(String gt) {
		this.gt = gt;
	}

	public GoodType getGoodtype() {
		return goodtype;
	}

	public void setGoodtype(GoodType goodtype) {
		this.goodtype = goodtype;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Good getGood() {
		return this.good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public List<ExtraAttrValue> getExtraattrvalues() {
		return extraattrvalues;
	}

	public void setExtraattrvalues(List<ExtraAttrValue> extraattrvalues) {
		this.extraattrvalues = extraattrvalues;
	}

	public String[] getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
}
