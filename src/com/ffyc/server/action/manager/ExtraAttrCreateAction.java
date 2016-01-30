package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.ExtraAttr;

public class ExtraAttrCreateAction extends BaseAction {
	
	private static final long serialVersionUID = 5227476013321067787L;
	private ExtraAttr extraattr = new ExtraAttr();

	public String execute() throws Exception {
		return "success";
	}

	public String create() throws Exception {
		this.extraattr.setId(getUuid());
		this.extraAttrService.save(this.extraattr);
		return "success";
	}

	public ExtraAttr getExtraattr() {
		return extraattr;
	}

	public void setExtraattr(ExtraAttr extraattr) {
		this.extraattr = extraattr;
	}


}
