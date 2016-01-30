package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Brand;

public class BrandCreateAction extends BaseUploadAction {

	private static final long serialVersionUID = 164275784995311912L;
	private Brand brand = new Brand();

	public String execute() throws Exception {
		return "success";
	}

	public String create() throws Exception {
		this.brand.setId(getUuid());
		Attachment attachment = upload();
		if(attachment!=null){
			this.brand.setLogoo(attachment);
			this.brand.setLogo(attachment.getId());
		}
		
		this.brandService.save(this.brand);
		return "success";
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}
}
