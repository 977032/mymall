package com.ffyc.server.action.manager;

import java.io.PrintWriter;

import com.ffyc.server.model.Spec;

public class SpecItemUpdateAjax extends BaseAction {
	private static final long serialVersionUID = -2212060361702379432L;
	private String id;
	private String value;
	private String utype = "";

	public String execute() throws Exception {
		Spec spec = this.specService.findById(this.id);
		PrintWriter out = this.response.getWriter();
		if (this.utype.equals("goodcode")) {
			spec.setGoodcode(this.value);
		} else if (this.utype.equals("inventory")) {
			Integer pinteger = spec.getInventory();
			try {
				pinteger = Integer.valueOf(Integer.parseInt(this.value));
			} catch (Exception localException) {
			}
			spec.setInventory(pinteger);
		} else if (this.utype.equals("price")) {
			Double pdouble = spec.getPrice();
			try {
				pdouble = Double.valueOf(Double.parseDouble(this.value));
			} catch (Exception localException1) {
			}
			spec.setPrice(pdouble);
		} else if (this.utype.equals("cprice")) {
			Double pdouble = spec.getCprice();
			try {
				pdouble = Double.valueOf(Double.parseDouble(this.value));
			} catch (Exception localException2) {
			}
			spec.setCprice(pdouble);
		} else if (this.utype.equals("weight")) {
			Double pdouble = new Double(0.0D);
			try {
				pdouble = Double.valueOf(Double.parseDouble(this.value));
			} catch (Exception localException3) {
			}
			spec.setWeight(pdouble);
		}
		this.specService.update(spec);
		out.print(this.value);
		return null;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUtype() {
		return this.utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}
}
