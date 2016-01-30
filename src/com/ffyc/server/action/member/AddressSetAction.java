package com.ffyc.server.action.member;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Address;

public class AddressSetAction extends BaseAction {
	private static final long serialVersionUID = -3140455772517839939L;
	private String str;
	private Address address = new Address();

	public String execute() throws Exception {
		if (StringUtils.isNotEmpty(this.str)) {
			this.address = this.addressService.findById(this.str);
			if (this.address != null) {
				setStr(this.address.getId());
				return "success";
			}
			return "error";
		}
		if (StringUtils.isNotEmpty(this.address.getAddress())) {
			this.address.setId(getUuid());
			this.address.setMember(getMemberFromSession().getId());
			this.address.setStatus("normal");
			this.addressService.update(this.address);
			setStr(this.address.getId());
			return "success";
		}
		return "error";
	}

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
