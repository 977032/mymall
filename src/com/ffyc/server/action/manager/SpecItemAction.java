package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.SpecItem;

public class SpecItemAction extends BaseAction {
	private static final long serialVersionUID = 3481105277377261256L;
	private List list;
	private String[] checkbox;

	public String execute() throws Exception {
		this.list = this.specItemService.findAll();
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				SpecItem specitem = this.specItemService.findById(this.checkbox[i]);
				if (specitem != null) {
					this.specItemService.delete(specitem.getId());
				}
			}
		}
		return "success";
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
