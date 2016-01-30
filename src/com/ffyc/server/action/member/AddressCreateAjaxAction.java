package com.ffyc.server.action.member;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Address;

public class AddressCreateAjaxAction extends BaseAction {

	private static final long serialVersionUID = 174045301811204998L;
	private Address address = new Address();
	private String result = "error";

	public String execute() throws Exception {
		if ((StringUtils.isNotEmpty(this.address.getAddress()))
				&& (StringUtils.isNotEmpty(this.address.getName()))
				&& (StringUtils.isNotEmpty(this.address.getTelephone()))) {
			if(this.address.getDistrictt()!=null){
				this.address.setDistrict(String.valueOf(this.address.getDistrictt().getId()));
			}
			this.address.setId(getUuid());
			this.address.setMember(getMemberFromSession().getId());
			this.address.setStatus(Address.STATUS_NORMAL);
			this.addressService.save(this.address);
			this.result = "success";
		}
		return "success";
	}

	@JSON(serialize = false)
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
