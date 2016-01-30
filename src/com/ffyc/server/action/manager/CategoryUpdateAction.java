package com.ffyc.server.action.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Category;

public class CategoryUpdateAction extends BaseUploadAction {
	private static final long serialVersionUID = 7358841937625567351L;
	private String c;
	private Category category;
	private List localelist;
	private List goodtypelist;
	private ArrayList listtree;

	public String execute() throws Exception {
		this.category = this.categoryService.findById(this.c);
		this.category.getLocalee();
		this.category.getCategory();
		this.category.getImagee();
		this.category.getGoodtypee();
		this.localelist = this.localeService.findAll();
		this.goodtypelist = this.goodTypeService.findAll();
		this.listtree = this.categoryService.findAllTree();
		return "success";
	}

	public String update() throws Exception {
		if (!this.category.getId().equals("")) {
			Category uc = this.categoryService.findById(this.category.getId());
			uc.setCsort(this.category.getCsort());
			uc.setDetail(this.category.getDetail());
			uc.setName(this.category.getName());
			uc.setLocale(this.category.getLocalee().getId());
			if ((this.category.getGoodtypee() != null)
					&& (this.category.getGoodtypee().getId() != null)
					&& (!this.category.getGoodtypee().getId().equals(""))) {
				uc.setGoodtype(this.category.getGoodtypee().getId());
			} else {
				uc.setGoodtype(null);
			}
			Attachment attachment = upload();
			Attachment oimage = uc.getImagee();
			if (attachment != null) {
				uc.setImagee(attachment);
				uc.setImage(attachment.getId());
			}
			String ucpid = "";
			if (uc.getCategory() != null) {
				ucpid = uc.getCategory().getId();
			}
			if (!this.category.getCategory().getId().equals(ucpid)) {
				Category sPcate = uc.getCategory();
				Category newPcate = this.categoryService.findById(this.category
						.getCategory().getId());
				String nodepath = "0>";
				if (newPcate != null) {
					nodepath = newPcate.getNodepath() + newPcate.getId() + ">";
					uc.setCategory(newPcate);
				} else {
					uc.setCategory(null);
				}
				uc.setNodepath(nodepath);
				this.categoryService.update(uc);
				if ((newPcate != null) && (newPcate.getNodetype().equals("F"))) {
					newPcate.setNodetype("D");
					this.categoryService.update(newPcate);
				}
				if (sPcate != null) {
					sPcate.getCategories().remove(uc);
					if (sPcate.getCategories().size() <= 0) {
						sPcate.setNodetype("F");
						this.categoryService.update(sPcate);
					}
				}
				Iterator it = uc.getCategories().iterator();
				while (it.hasNext()) {
					Category c = (Category) it.next();
					String np = "0>";
					np = uc.getNodepath() + uc.getId() + ">";
					c.setNodepath(np);
					this.categoryService.update(c);
				}
			} else {
				this.categoryService.update(uc);
			}
			this.categoryService.update(uc);
			if ((attachment != null) && (oimage != null)) {
				this.attachmentService.delete(oimage.getId());
			}
		}
		return "success";
	}

	public String getC() {
		return this.c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public ArrayList getListtree() {
		return this.listtree;
	}

	public void setListtree(ArrayList listtree) {
		this.listtree = listtree;
	}
}
