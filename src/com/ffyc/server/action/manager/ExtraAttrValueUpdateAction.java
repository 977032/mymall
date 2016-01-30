package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.ExtraAttrValue;

public class ExtraAttrValueUpdateAction extends BaseAction {
	private static final long serialVersionUID = 7925938088881790865L;
	private List<ExtraAttrValue> extraattrvalue;
	private String ea;

	public String execute() throws Exception {
		for (int i = 0; i < this.extraattrvalue.size(); i++) {
			ExtraAttrValue evae = (ExtraAttrValue) this.extraattrvalue.get(i);
			if (evae.getId() != null) {
				ExtraAttrValue uev = this.extraAttrValueService.findById(evae
						.getId());
				uev.setValue(evae.getValue());
				uev.setCsort(evae.getCsort());
				this.extraAttrValueService.update(uev);
			}
		}
		return "success";
	}

	public List<ExtraAttrValue> getExtraattrvalue() {
		return extraattrvalue;
	}



	public void setExtraattrvalue(List<ExtraAttrValue> extraattrvalue) {
		this.extraattrvalue = extraattrvalue;
	}



	public String getEa() {
		return this.ea;
	}

	public void setEa(String ea) {
		this.ea = ea;
	}
}
