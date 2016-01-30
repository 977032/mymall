package com.ffyc.server.action.manager;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.model.DocumentCategory;

public class DocumentCategoryAction extends BaseAction {
	private static final long serialVersionUID = -2258406736932325123L;
	private String pc;
	private DocumentCategory pdoccate;
	private List list;
	private String[] checkbox;

	public String execute() throws Exception {
		DocumentCategory dc = new DocumentCategory();
		if (StringUtils.isNotEmpty(this.pc)) {
			this.pdoccate = this.documentCategoryService.findById(this.pc);
			dc.setPid(this.pc);
		} else {
			dc.setPid("null");
		}
		this.list = this.documentCategoryService.findByDocumentCategory(dc);
		Iterator it = list.iterator();
		while(it.hasNext()){
			DocumentCategory documentCategory = (DocumentCategory)it.next();
			documentCategory.getImagee();
		}
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				DocumentCategory doccate = this.documentCategoryService
						.findById(this.checkbox[i]);
				this.documentCategoryService.delete(doccate.getId());
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

	public DocumentCategory getPdoccate() {
		return this.pdoccate;
	}

	public void setPdoccate(DocumentCategory pdoccate) {
		this.pdoccate = pdoccate;
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
