package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.GoodType;

public class ExtraAttrAction extends BaseAction {
	private List list;
	private String[] checkbox;
	private String gt;
	private GoodType goodtype;

	public String execute() throws Exception {
		if (StringUtils.isNotEmpty(this.gt)) {
			this.goodtype = this.goodTypeService.findById(this.gt);
			this.list = this.extraAttrService.findAllByGoodType(this.goodtype.getId());
		}else{
			this.list = this.extraAttrService.findAll();
		}
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				ExtraAttr extattr = this.extraAttrService
						.findById(this.checkbox[i]);
				if (extattr != null) {
					this.extraAttrService.delete(extattr.getId());
				}
			}
		}
		return "success";
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String[] getCheckbox() {
		return this.checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}

	public String getGt() {
		return this.gt;
	}

	public void setGt(String gt) {
		this.gt = gt;
	}

	public GoodType getGoodtype() {
		return goodtype;
	}

	public void setGoodtype(GoodType goodtype) {
		this.goodtype = goodtype;
	}

}
