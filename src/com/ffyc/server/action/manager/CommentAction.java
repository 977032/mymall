package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Comment;
import com.ffyc.server.utils.PaginationSupport;

public class CommentAction extends BaseAction {

	private static final long serialVersionUID = 1365591567830380077L;
	private PaginationSupport ps;
	private int pagesize = 20;
	private int page = 1;
	private String keyword;
	private String[] checkbox;
	private String cm;
	private String status;

	public String execute() throws Exception {
		Comment dc = new Comment();
		if (this.keyword != null) {
			dc.setContent(this.keyword);
		}
		this.ps = this.commentService.findPageByComment(dc, this.page,
				this.pagesize);
		return "success";
	}

	public String update() throws Exception {
		if ((this.cm != null) && (this.status != null)) {
			Comment comment = this.commentService.findById(this.cm);
			comment.setStatus(this.status);
			this.commentService.update(comment);
		}
		return "success";
	}

	public String delete() throws Exception {
		if ((this.checkbox != null) && (this.checkbox.length > 0)) {
			for (int i = 0; i < this.checkbox.length; i++) {
				Comment comment = this.commentService.findById(this.checkbox[i]);
				if (comment != null) {
					this.commentService.delete(comment.getId());
				}
			}
		}
		return "success";
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

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String[] getCheckbox() {
		return this.checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}

	public String getCm() {
		return this.cm;
	}

	public void setCm(String cm) {
		this.cm = cm;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
