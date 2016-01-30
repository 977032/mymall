package com.ffyc.server.action.member;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Address;

public class AddressCreateAction extends BaseAction {
	private static final long serialVersionUID = -4696749697846148829L;
	private Address address = new Address();
	private List districtroot;

	public String execute() throws Exception {
		this.districtroot = this.districtService.findAllRoot();
		return "success";
	}

	public String create() throws Exception {
		if (StringUtils.isNotEmpty(this.address.getId())) {
			Address std = this.addressService.findById(this.address.getId());
			std.setAddress(this.address.getAddress());
			std.setDistrict(String.valueOf(this.address.getDistrictt().getId()));
			std.setMobile(this.address.getMobile());
			std.setName(this.address.getName());
			std.setPostcode(this.address.getPostcode());
			std.setTelephone(this.address.getTelephone());
			std.setStatus(this.address.getStatus());
			this.addressService.update(std);
		} else {
			this.address.setId(getUuid());
			this.address.setMember(getMemberFromSession().getId());
			this.address.setStatus("normal");
			this.address.setDistrict(String.valueOf(this.address.getDistrictt().getId()));
			this.addressService.save(this.address);
		}
		return "success";
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List getDistrictroot() {
		return this.districtroot;
	}

	public void setDistrictroot(List districtroot) {
		this.districtroot = districtroot;
	}
}
