package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Area;

public class AreaUpdateAction extends BaseAction {
	private static final long serialVersionUID = 8437341050040486352L;
	private List<Area> areas;
	private String ls;

	public String execute() throws Exception {
		for (int i = 0; i < this.areas.size(); i++) {
			Area area = (Area) this.areas.get(i);
			if (area.getId() != null) {
				Area uls = this.areaService.findById(area.getId());
				uls.setWfirst(area.getWfirst());
				uls.setWfcost(area.getWfcost());
				uls.setWappend(area.getWappend());
				uls.setWacost(area.getWacost());
				uls.setCod(area.getCod());
				this.areaService.update(uls);
				
				setLs(uls.getLogisticc().getId());
			}
		}
		return "success";
	}

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public String getLs() {
		return this.ls;
	}

	public void setLs(String ls) {
		this.ls = ls;
	}
}
