package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Category;

public class CategoryAction extends BaseAction {
	private static final long serialVersionUID = -8495592155847769843L;
	private String pc;
	private Category pcategory;
	private List list;
	private String[] checkbox;

	public String execute() throws Exception {
		Category dc = new Category();
		if (StringUtils.isNotEmpty(this.pc)) {
			this.pcategory = this.categoryService.findById(this.pc);
			dc.setPid(this.pcategory.getId());
		} else {
			dc.setPid("null");
		}
		this.list = this.categoryService.findByCategory(dc);
		this.categoryService.getLocales(this.list);
		this.categoryService.getImagees(this.list);
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				Category category = this.categoryService.findById(this.checkbox[i]);
				this.categoryService.delete(category.getId());
			}
		}
		return "success";
	}

	public String getPc() {
		return this.pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public Category getPcategory() {
		return this.pcategory;
	}

	public void setPcategory(Category pcategory) {
		this.pcategory = pcategory;
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
}
