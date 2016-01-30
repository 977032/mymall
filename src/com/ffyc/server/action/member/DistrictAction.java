package com.ffyc.server.action.member;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.District;

public class DistrictAction extends BaseAction {
	private static final long serialVersionUID = 6076906051399982150L;
	private int id;
	private List list;

	public String execute() throws Exception {
		if (this.id > 0) {
			District district = this.districtService.findById(Integer
					.valueOf(this.id));
			if (district != null) {
				District dc = new District();
				dc.setPid(district.getId());

				this.list = this.districtService.findByDistrict(dc);
			}
		}
		return "success";
	}

	@JSON(serialize = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List getList() {
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
