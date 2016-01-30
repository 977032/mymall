package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.SpecItem;
import com.ffyc.server.model.SpecValue;

public class SpecValueAction extends BaseAction {
	private static final long serialVersionUID = 3924680014596450574L;
	private String si;
	private SpecItem specitem;
	private List list;
	private String[] checkbox;

	public String execute() throws Exception {
		this.specitem = this.specItemService.findById(this.si);
		SpecValue dc = new SpecValue();
		dc.setSpecitem(this.specitem.getId());
		this.list = this.specValueService.findBySpecValue(dc);
		this.specValueService.getImagees(list);
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)){
			for (int i = 0; i < this.checkbox.length; i++) {
				SpecValue specvalue = this.specValueService
						.findById(this.checkbox[i]);
				if (specvalue != null) {
					this.specValueService.delete(specvalue.getId());
				}
			}
		}
		return "success";
	}

	public String getSi() {
		return this.si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public SpecItem getSpecitem() {
		return specitem;
	}

	public void setSpecitem(SpecItem specitem) {
		this.specitem = specitem;
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
