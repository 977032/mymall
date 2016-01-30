package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.SpecItem;
import com.ffyc.server.model.SpecValue;

public class SpecValueCreateAction extends BaseUploadAction {
	private static final long serialVersionUID = -810488010621392911L;
	private String si;
	private SpecItem specitem;
	private SpecValue specvalue = new SpecValue();

	public String execute() throws Exception {
		this.specitem = this.specItemService.findById(this.si);
		return "success";
	}

	public String create() throws Exception {
		this.specitem = this.specItemService.findById(this.si);
		if (this.specitem != null) {
			this.specvalue.setId(getUuid());
			this.specvalue.setSpecitemm(this.specitem);
			this.specvalue.setSpecitem(this.specitem.getId());
			Attachment attachment = upload();
			if (attachment != null) {
				this.specvalue.setImagee(attachment);
				this.specvalue.setImage(attachment.getId());
			}
			this.specValueService.save(this.specvalue);
		}
		return "success";
	}

	public String getSi() {
		return this.si;
	}

	public void setSi(String si) {
		this.si = si;
	}

	public SpecItem getSpecitem() {
		return specitem;
	}

	public void setSpecitem(SpecItem specitem) {
		this.specitem = specitem;
	}

	public SpecValue getSpecvalue() {
		return specvalue;
	}

	public void setSpecvalue(SpecValue specvalue) {
		this.specvalue = specvalue;
	}

}
