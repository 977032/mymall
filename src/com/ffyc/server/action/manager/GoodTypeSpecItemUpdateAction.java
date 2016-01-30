package com.ffyc.server.action.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ffyc.server.model.GoodType;
import com.ffyc.server.model.SpecItem;

public class GoodTypeSpecItemUpdateAction extends BaseAction {
	private static final long serialVersionUID = -1829701346600379754L;
	private String gt;
	private GoodType goodtype;
	private List list;
	private String[] checkbox;

	public String execute() throws Exception {
		this.goodtype = this.goodTypeService.findById(this.gt);
		this.list = this.specItemService.findAll();
		return "success";
	}

	public String update() throws Exception {
		this.goodtype = this.goodTypeService.findById(this.gt);
		this.goodtype.setSpecitems(new ArrayList<SpecItem>());
		List<SpecItem> list = new ArrayList<SpecItem>();
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				SpecItem specitem = this.specItemService.findById(this.checkbox[i]);
				this.goodtype.getSpecitems().add(specitem);
				list.add(specitem);
			}
		}
		this.goodtype.deleteSpecItems();
		if(this.goodtype.getSpecitems().size()>0){
			this.goodtype.insertSpecItems();
		}
		return "success";
	}

	public String getGt() {
		return this.gt;
	}

	public void setGt(String gt) {
		this.gt = gt;
	}

	public GoodType getGoodtype() {
		return goodtype;
	}

	public void setGoodtype(GoodType goodtype) {
		this.goodtype = goodtype;
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String[] getCheckbox() {
		return this.checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}
}
