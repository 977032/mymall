package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Brand;

public class BrandUpdateAction extends BaseUploadAction {
	private static final long serialVersionUID = -4657548979204381891L;
	private String bd;
	private Brand brand;

	public String execute() throws Exception {
		this.brand = this.brandService.findById(this.bd);
		this.brand.getLogoo();
		return "success";
	}

	public String update() throws Exception {
		Brand ubd = this.brandService.findById(this.brand.getId());
		ubd.setName(this.brand.getName());
		ubd.setAlias(this.brand.getAlias());
		ubd.setIntro(this.brand.getIntro());
		ubd.setUrl(this.brand.getUrl());
		ubd.setCsort(this.brand.getCsort());
		Attachment attachment = upload();
		Attachment ologo = ubd.getLogoo();
		if (attachment != null) {
			ubd.setLogoo(attachment);
			ubd.setLogo(attachment.getId());
		}
		this.brandService.update(ubd);
		if ((attachment != null) && (ologo != null)) {
			this.attachmentService.delete(ologo.getId());
		}
		return "success";
	}

	public String getBd() {
		return this.bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
