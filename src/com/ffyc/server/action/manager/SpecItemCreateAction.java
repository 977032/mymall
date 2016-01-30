package com.ffyc.server.action.manager;

import com.ffyc.server.model.SpecItem;

public class SpecItemCreateAction extends BaseAction {

	private static final long serialVersionUID = -8445430589673484796L;
	private SpecItem specitem = new SpecItem();

	public String execute() throws Exception {
		return "success";
	}

	public String create() throws Exception {
		this.specitem.setId(getUuid());
		this.specItemService.save(this.specitem);
		return "success";
	}

	public SpecItem getSpecitem() {
		return specitem;
	}

	public void setSpecitem(SpecItem specitem) {
		this.specitem = specitem;
	}
}
