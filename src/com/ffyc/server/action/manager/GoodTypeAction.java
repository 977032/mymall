package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.model.GoodType;

public class GoodTypeAction extends BaseAction {
	private static final long serialVersionUID = -2919177837901751384L;
	private List list;
	private String[] checkbox;

	public String execute() throws Exception {
		this.list = this.goodTypeService.findAll();
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				GoodType goodtype = this.goodTypeService.findById(this.checkbox[i]);
				this.goodTypeService.delete(goodtype.getId());
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
