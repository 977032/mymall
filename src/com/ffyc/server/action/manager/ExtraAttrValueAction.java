package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.model.ExtraAttr;

public class ExtraAttrValueAction extends BaseAction {

	private static final long serialVersionUID = 8714557967936772279L;
	private String ea;
	private ExtraAttr extraattr;
	private List list;
	private String ev;

	public String execute() throws Exception {
		this.extraattr = this.extraAttrService.findById(this.ea);
		ExtraAttrValue dc = new ExtraAttrValue();
		dc.setExtraattr(this.extraattr.getId());
		this.list = this.extraAttrValueService.findByExtraAttrValue(dc);
		return "success";
	}

	public String delete() throws Exception {
		ExtraAttrValue extraattrvalue = this.extraAttrValueService
				.findById(this.ev);
		if (extraattrvalue != null) {
			this.extraAttrValueService.delete(extraattrvalue.getId());
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

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getEv() {
		return this.ev;
	}

	public void setEv(String ev) {
		this.ev = ev;
	}
}
