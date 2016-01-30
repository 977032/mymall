package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.model.Manager;

public class ManagerAction extends BaseAction {
	private static final long serialVersionUID = -7915439982960780887L;
	private List list;
	private String[] checkbox;

	public String execute() throws Exception {
		this.list = this.managerService.findAll();
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				Manager manager = this.managerService.findById(this.checkbox[i]);
				if (manager != null) {
					this.managerService.delete(manager.getId());
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
