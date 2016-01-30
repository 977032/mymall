package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.CommentMapper;
import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.utils.BeanFactory;

public class Comment implements Serializable {

	private String id;
	private String member;
	private Member memberr;
	private String good;
	private Good goodd;
	private String pid;
	private Comment comment;
	private String title;
	private String content;
	private String status;
	private Timestamp ctime;
	private Timestamp utime;
	private List<Comment> comments;
	
	public final static String STATUS_AUDIT = "audit";
	public final static String STATUS_NORMAL = "normal";

	public Comment() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Member getMemberr() {
		if(StringUtils.isNotEmpty(this.member) && this.memberr == null){
			MemberMapper memberMapper = BeanFactory.getInstance().getMemberMapper();
			this.memberr = memberMapper.findById(this.member);
		}
		return memberr;
	}

	public void setMemberr(Member memberr) {
		this.memberr = memberr;
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public Good getGoodd() {
		if(StringUtils.isNotEmpty(this.good) && this.goodd == null){
			GoodMapper goodMapper = BeanFactory.getInstance().getGoodMapper();
			this.goodd = goodMapper.findById(this.good);
		}
		return goodd;
	}

	public void setGoodd(Good goodd) {
		this.goodd = goodd;
	}

	public Comment getComment() {
		if(StringUtils.isNotEmpty(this.pid) && this.comment == null){
			CommentMapper commentMapper = BeanFactory.getInstance().getCommentMapper();
			this.comment = commentMapper.findById(this.pid);
		}
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getUtime() {
		return utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<Comment> getComments() {
		if(this.comments == null){
			CommentMapper commentMapper = BeanFactory.getInstance().getCommentMapper();
			Comment dc = new Comment();
			dc.setPid(this.id);
			this.comments = commentMapper.findByComment(dc, null, null);
		}
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
