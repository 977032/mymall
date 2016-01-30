package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.model.ExtraAttr;

public class ExtraAttrValueCreateAction extends BaseAction {
	private static final long serialVersionUID = -2223984703535247174L;
	private String ea;
	private ExtraAttr extraattr;
	private ExtraAttrValue extraattrvalue = new ExtraAttrValue();

	public String execute() throws Exception {
		this.extraattr = this.extraAttrService.findById(this.ea);
		if (this.extraattr != null) {
			this.extraattrvalue.setId(getUuid());
			this.extraattrvalue.setExtraattrr(this.extraattr);
			this.extraattrvalue.setExtraattr(this.extraattr.getId());
			this.extraAttrValueService.save(this.extraattrvalue);
		}
		return "success";
	}

	public String getEa() {
		return this.ea;
	}

	public void setEa(String ea) {
		this.ea = ea;
	}

	public ExtraAttr getExtraattr() {
		return extraattr;
	}

	public void setExtraattr(ExtraAttr extraattr) {
		this.extraattr = extraattr;
	}

	public ExtraAttrValue getExtraattrvalue() {
		return extraattrvalue;
	}

	public void setExtraattrvalue(ExtraAttrValue extraattrvalue) {
		this.extraattrvalue = extraattrvalue;
	}

}
