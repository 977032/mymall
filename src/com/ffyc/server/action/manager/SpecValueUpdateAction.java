package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.SpecValue;

public class SpecValueUpdateAction extends BaseUploadAction {
	
	private static final long serialVersionUID = 2338778887456925449L;
	private String sv;
	private SpecValue specvalue;
	private String si;

	public String execute() throws Exception {
		this.specvalue = this.specValueService.findById(this.sv);
		return "success";
	}

	public String update() throws Exception {
		SpecValue usv = this.specValueService.findById(this.specvalue.getId());
		usv.setName(this.specvalue.getName());
		Attachment attachment = upload();
		Attachment oimage = usv.getImagee();
		if (attachment != null) {
			usv.setImagee(attachment);
			usv.setImage(attachment.getId());
		}
		this.specValueService.update(usv);
		if ((attachment != null) && (oimage != null)) {
			this.attachmentService.delete(oimage.getId());
		}
		setSi(usv.getSpecitem());
		return "success";
	}

	public String getSv() {
		return this.sv;
	}

	public void setSv(String sv) {
		this.sv = sv;
	}

	public SpecValue getSpecvalue() {
		return this.specvalue;
	}

	public void setSpecvalue(SpecValue specvalue) {
		this.specvalue = specvalue;
	}

	public String getSi() {
		return this.si;
	}

	public void setSi(String si) {
		this.si = si;
	}
}
