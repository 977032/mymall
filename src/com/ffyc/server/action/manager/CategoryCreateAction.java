package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Category;

public class CategoryCreateAction extends BaseUploadAction {
	private static final long serialVersionUID = 185449411565655042L;
	private List localelist;
	private List goodtypelist;
	private Category category = new Category();
	private String pc;
	private Category pcategory;

	public String execute() throws Exception {
		this.localelist = this.localeService.findAll();
		this.goodtypelist = this.goodTypeService.findAll();
		if (StringUtils.isNotEmpty(this.pc)) {
			this.pcategory = this.categoryService.findById(this.pc);
		}
		return "success";
	}

	public String create() throws Exception {
		Attachment attachment = upload();
		if(attachment!=null){
			this.category.setImagee(attachment);
			this.category.setImage(attachment.getId());
		}
		this.category.setId(getUuid());
		if (StringUtils.isNotEmpty(this.pc)) {
			this.pcategory = this.categoryService.findById(this.pc);
		}
		String nodepath = "0>";
		if ((this.pcategory != null) && (this.pcategory.getId() != null)) {
			nodepath = this.pcategory.getNodepath() + this.pcategory.getId()
					+ ">";
		}
		this.category.setNodepath(nodepath);
		this.category.setNodetype("F");
		this.category.setCategory(this.pcategory);
		this.category.setLocale(this.category.getLocalee().getId());
		if ((this.category.getGoodtypee() == null)
				|| (this.category.getGoodtypee().getId() == null)
				|| (this.category.getGoodtypee().getId().equals(""))) {
			this.category.setGoodtype(null);
		} else {
			this.category.setGoodtype(this.category.getGoodtypee().getId());
		}
		this.categoryService.save(this.category);
		if ((this.pcategory != null) && (this.pcategory.getId() != null)) {
			this.pcategory.setNodetype("D");
			this.categoryService.update(this.pcategory);
		}
		return "success";
	}

	public List getLocalelist() {
		return this.localelist;
	}

	public void setLocalelist(List localelist) {
		this.localelist = localelist;
	}

	public List getGoodtypelist() {
		return this.goodtypelist;
	}

	public void setGoodtypelist(List goodtypelist) {
		this.goodtypelist = goodtypelist;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
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
}
