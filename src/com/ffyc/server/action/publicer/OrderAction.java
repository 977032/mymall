package com.ffyc.server.action.publicer;

import java.util.List;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Member;

public class OrderAction extends BaseAction {
	private static final long serialVersionUID = -5711993706707364694L;
	private Member member;
	private List districtroot;
	private List freightList;
	private List paymodeList;
	private List addressList;

	public String execute() throws Exception {
		GlobalChannelContent(this.map);
		if (getMemberFromSession() != null) {
			this.member = this.memberService.findById(getMemberFromSession()
					.getId());
			this.districtroot = this.districtService.findAllRoot();
			this.paymodeList = this.payModeService.findAll();
			return "success";
		}
		return "login";
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getDistrictroot() {
		return this.districtroot;
	}

	public void setDistrictroot(List districtroot) {
		this.districtroot = districtroot;
	}

	public List getFreightList() {
		return this.freightList;
	}

	public void setFreightList(List freightList) {
		this.freightList = freightList;
	}

	public List getPaymodeList() {
		return this.paymodeList;
	}

	public void setPaymodeList(List paymodeList) {
		this.paymodeList = paymodeList;
	}

	public List getAddressList() {
		return this.addressList;
	}

	public void setAddressList(List addressList) {
		this.addressList = addressList;
	}
}
