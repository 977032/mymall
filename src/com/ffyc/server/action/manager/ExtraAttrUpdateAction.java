package com.ffyc.server.action.manager;

import java.io.PrintWriter;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.ExtraAttr;

public class ExtraAttrUpdateAction extends BaseAction {
	private static final long serialVersionUID = -2008094806227437192L;
	private String ea;
	private ExtraAttr extraattr;
	private String pvalue = "";

	public String execute() throws Exception {
		this.extraattr = this.extraAttrService.findById(this.ea);
		return "success";
	}

	public String update() throws Exception {
		ExtraAttr uea = this.extraAttrService.findById(this.extraattr.getId());
		uea.setName(this.extraattr.getName());
		uea.setAlias(this.extraattr.getAlias());
		uea.setViewtype(this.extraattr.getViewtype());
		uea.setDisplay(this.extraattr.getDisplay());
		uea.setCsort(this.extraattr.getCsort());
		this.extraAttrService.update(uea);
		return "success";
	}

	public String updateDisplay() throws Exception {
		ExtraAttr uea = this.extraAttrService.findById(this.ea);
		PrintWriter out = this.response.getWriter();
		if ((this.pvalue.equals("yes")) || (this.pvalue.equals("no"))) {
			uea.setDisplay(this.pvalue);
			this.extraAttrService.update(uea);
			out.print(this.pvalue);
		} else {
			out.print("错误");
		}
		return null;
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

	public String getPvalue() {
		return this.pvalue;
	}

	public void setPvalue(String pvalue) {
		this.pvalue = pvalue;
	}
}
