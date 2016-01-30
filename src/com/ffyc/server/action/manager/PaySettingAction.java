package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.model.PaySetting;

public class PaySettingAction extends BaseAction {
	private static final long serialVersionUID = 6043688555885290392L;
	private List list;
	private String[] checkbox;
	private String pm;

	public String execute() throws Exception {
		this.list = this.paySettingService.findAll();
		return "success";
	}

	public String delete() throws Exception {
		for (int i = 0; i < this.checkbox.length; i++) {
			PaySetting setting = this.paySettingService.findById(this.checkbox[i]);
			if (setting != null) {
				this.paySettingService.delete(setting.getId());
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

	public String getPm() {
		return this.pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}
}
