package com.ffyc.server.action.publicer;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Comment;
import com.ffyc.server.model.Good;
import com.opensymphony.xwork2.ActionContext;

public class CommentSubmitAction extends BaseAction {
	
	private static final long serialVersionUID = 1133379219066627L;
	private String gid;
	private String content;
	private String validateCode;
	private String result;

	public String execute() throws Exception {
		String verifyCode = (String) ActionContext.getContext().getSession()
				.get("VerifyCode");
		if ((verifyCode != null) && (verifyCode.equals(this.validateCode))) {
			Good good = this.goodService.findById(this.gid);
			if (good != null) {
				String status = Comment.STATUS_AUDIT;
				Comment comment = new Comment();
				comment.setId(getUuid());
				comment.setGood(good.getId());
				comment.setContent(this.content);
				if (getMemberFromSession() != null) {
					comment.setMember(getMemberFromSession().getId());
				}
				comment.setCtime(getTimestamp());
				comment.setStatus(status);
				this.commentService.save(comment);
				if ((status != null) && (!status.equals(Comment.STATUS_NORMAL))) {
					this.result = "评论提交成功，等待审核";
				} else {
					this.result = "评论提交成功";
				}
			} else {
				this.result = "信息错误";
			}
		} else {
			this.result = "验证码错误";
		}
		return "success";
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getValidateCode() {
		return this.validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
