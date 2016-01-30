package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.DocumentCategory;

public class DocumentCategoryCreateAction extends BaseUploadAction {
	private static final long serialVersionUID = -5315978049455273173L;
	private List localelist;
	private DocumentCategory doccate = new DocumentCategory();
	private String pc;
	private DocumentCategory pdoccate;

	public String execute() throws Exception {
		this.localelist = this.localeService.findAll();
		if (StringUtils.isNotEmpty(this.pc)) {
			this.pdoccate = this.documentCategoryService.findById(this.pc);
		}
		return "success";
	}

	public String create() throws Exception {
		Attachment attachment = upload();
		if(attachment!=null){
			this.doccate.setImagee(attachment);
			this.doccate.setImage(attachment.getId());
		}
		this.doccate.setId(getUuid());
		this.doccate.setLocale(this.doccate.getLocalee().getId());

		if (StringUtils.isNotEmpty(this.pc)) {
			this.pdoccate = this.documentCategoryService.findById(this.pc);
			this.doccate.setPid(this.pc);
		}else{
			this.doccate.setPid(null);
		}
		String nodepath = "0>";
		if ((this.pdoccate != null) && (this.pdoccate.getId() != null)) {
			nodepath = this.pdoccate.getNodepath() + this.pdoccate.getId()
					+ ">";
		}
		this.doccate.setNodepath(nodepath);
		this.doccate.setNodetype("F");
		this.doccate.setDoccate(this.pdoccate);
		
		this.documentCategoryService.save(this.doccate);
		if ((this.pdoccate != null) && (this.pdoccate.getId() != null)) {
			this.pdoccate.setNodetype("D");
			this.documentCategoryService.update(this.pdoccate);
		}
		return "success";
	}

	public List getLocalelist() {
		return this.localelist;
	}

	public void setLocalelist(List localelist) {
		this.localelist = localelist;
	}

	public DocumentCategory getDoccate() {
		return this.doccate;
	}

	public void setDoccate(DocumentCategory doccate) {
		this.doccate = doccate;
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
}
