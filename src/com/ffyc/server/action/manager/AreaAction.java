package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.mapper.LogisticMapper;
import com.ffyc.server.model.Area;
import com.ffyc.server.model.Logistic;
import com.ffyc.server.utils.BeanFactory;
import com.ffyc.server.utils.PaginationSupport;

public class AreaAction extends BaseAction {
	private static final long serialVersionUID = 6784816975988245444L;
	private String ls;
	private Logistic logistic;
	private PaginationSupport ps;
	private int pagesize = 20;
	private int page = 1;
	private String la;
	private List districtroot;

	public String execute() throws Exception {
		this.logistic = this.logisticService.findById(this.ls);
		this.districtroot = this.districtService.findAllRoot();
		Area area = new Area();
		area.setLogistic(this.ls);
		this.ps = this.areaService.findPageByArea(area, this.page,this.pagesize);
		return "success";
	}

	public String delete() throws Exception {
		Area area = this.areaService.findById(this.la);
		if (area != null) {
			this.areaService.delete(area.getId());
		}
		return "success";
	}

	public String getLs() {
		return this.ls;
	}

	public void setLs(String ls) {
		this.ls = ls;
	}

	public Logistic getLogistic() {
			return logistic;
	}

	public void setLogistic(Logistic logistic) {
		this.logistic = logistic;
	}

	public PaginationSupport getPs() {
		return this.ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public int getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getLa() {
		return this.la;
	}

	public void setLa(String la) {
		this.la = la;
	}

	public List getDistrictroot() {
		return this.districtroot;
	}

	public void setDistrictroot(List districtroot) {
		this.districtroot = districtroot;
	}
}
