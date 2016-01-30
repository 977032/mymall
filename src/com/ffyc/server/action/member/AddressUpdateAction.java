package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Address;

public class AddressUpdateAction extends BaseAction {

	private static final long serialVersionUID = 7353071161113602394L;
	private String str;
	private Address address;

	public String execute() throws Exception {
		this.address = this.addressService.findById(this.str);
		return "success";
	}

	public String update() throws Exception {
		Address ustr = this.addressService.findById(this.address.getId());
		if (ustr != null) {
			ustr.setAddress(this.address.getAddress());
			ustr.setPostcode(this.address.getPostcode());
			ustr.setTelephone(this.address.getTelephone());
			ustr.setMobile(this.address.getMobile());
			ustr.setName(this.address.getName());
			this.addressService.update(ustr);
		}
		return "success";
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
