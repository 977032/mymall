package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.SpecItem;

public class SpecItemUpdateAction extends BaseAction {
	private static final long serialVersionUID = 8749576590133113044L;
	private String si;
	private SpecItem specitem;

	public String execute() throws Exception {
		this.specitem = this.specItemService.findById(this.si);
		return "success";
	}

	public String update() throws Exception {
		SpecItem usi = this.specItemService.findById(this.specitem.getId());
		usi.setName(this.specitem.getName());
		usi.setRemark(this.specitem.getRemark());
		usi.setDtype(this.specitem.getDtype());
		usi.setDstyle(this.specitem.getDstyle());
		this.specItemService.update(usi);
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

}
