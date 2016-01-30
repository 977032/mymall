package com.ffyc.server.action.publicer;

import java.util.List;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Address;
import com.ffyc.server.model.District;

public class GetFreightAction extends BaseAction {
	
	private static final long serialVersionUID = 2320040571009241875L;
	private String str;
	private List list;

	public String execute() throws Exception {
		Address address = this.addressService.findById(this.str);
		if (address != null) {
			District district = address.getDistrictt();
			this.list = areaService.findAll();
		}
		return "success";
	}

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
