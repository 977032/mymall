package com.ffyc.server.action.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.ffyc.server.model.Brand;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.GoodType;
import com.ffyc.server.model.SpecItem;

public class GoodTypeCreateAction extends BaseAction {

	private static final long serialVersionUID = -4503065726652649186L;
	private List list;
	private List silist;
	private List ealist;
	private GoodType goodtype = new GoodType();
	private String[] checkbox;
	private String[] sicheckbox;
	private String[] eacheckbox;

	public String execute() throws Exception {
		this.list = this.brandService.findAll();
		this.silist = this.specItemService.findAll();
		this.ealist = this.extraAttrService.findAll();
		return "success";
	}

	public String create() throws Exception {
		this.goodtype.setId(getUuid());
		this.goodTypeService.save(this.goodtype);
		this.goodtype.setBrands(new ArrayList<Brand>());
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				Brand brand = this.brandService.findById(this.checkbox[i]);
				this.goodtype.getBrands().add(brand);
			}
		}
		this.goodtype.setSpecitems(new ArrayList<SpecItem>());
		if ((this.sicheckbox != null) && (this.sicheckbox.length > 0)) {
			if ((this.sicheckbox != null) && (this.sicheckbox.length > 0)) {
				for (int i = 0; i < this.sicheckbox.length; i++) {
					SpecItem specitem = this.specItemService
							.findById(this.sicheckbox[i]);
					this.goodtype.getSpecitems().add(specitem);
				}
			}
		}
		
		this.goodtype.setExtraattrs(new ArrayList<ExtraAttr>());
		if ((this.eacheckbox != null) && (this.eacheckbox.length > 0)) {
			if ((this.eacheckbox != null) && (this.eacheckbox.length > 0)) {
				for (int i = 0; i < this.eacheckbox.length; i++) {
					ExtraAttr extraattr = this.extraAttrService
							.findById(this.eacheckbox[i]);
					this.goodtype.getExtraattrs().add(extraattr);
				}
			}
		}
		
		this.goodTypeService.update(this.goodtype);
		return "success";
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List getSilist() {
		return this.silist;
	}

	public void setSilist(List silist) {
		this.silist = silist;
	}

	public List getEalist() {
		return this.ealist;
	}

	public void setEalist(List ealist) {
		this.ealist = ealist;
	}

	public GoodType getGoodtype() {
		return this.goodtype;
	}

	public void setGoodtype(GoodType goodtype) {
		this.goodtype = goodtype;
	}

	public String[] getCheckbox() {
		return this.checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}

	public String[] getSicheckbox() {
		return this.sicheckbox;
	}

	public void setSicheckbox(String[] sicheckbox) {
		this.sicheckbox = sicheckbox;
	}

	public String[] getEacheckbox() {
		return this.eacheckbox;
	}

	public void setEacheckbox(String[] eacheckbox) {
		this.eacheckbox = eacheckbox;
	}
}
