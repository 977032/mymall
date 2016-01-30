package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.ChannelContentMapper;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.utils.BeanFactory;

public class Channel implements Serializable {

	private String id;
	private String manager;
	private Manager managerr;
	private String template;
	private Attachment templatee;
	private String vtemplate;
	private Attachment vtemplatee;
	private String title;
	private String intro;
	private Timestamp ctime;
	private Timestamp utime;
	private List<ChannelContent> chcontents;

	public Channel() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Manager getManagerr() {
		if(StringUtils.isNotEmpty(this.manager) && this.managerr == null){
			ManagerMapper managerMapper = BeanFactory.getInstance().getManagerMapper();
			this.managerr = managerMapper.findById(this.manager);
		}
		return managerr;
	}

	public void setManagerr(Manager managerr) {
		this.managerr = managerr;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public Attachment getTemplatee() {
		if(StringUtils.isNotEmpty(this.template) && this.templatee == null){
			AttachmentMapper attachmentMapper = BeanFactory.getInstance().getAttachmentMapper();
			this.templatee = attachmentMapper.findById(this.template);
		}
		return templatee;
	}

	public void setTemplatee(Attachment templatee) {
		this.templatee = templatee;
	}

	public String getVtemplate() {
		return vtemplate;
	}

	public void setVtemplate(String vtemplate) {
		this.vtemplate = vtemplate;
	}

	public Attachment getVtemplatee() {
		if(StringUtils.isNotEmpty(this.vtemplate) & this.vtemplatee == null){
			AttachmentMapper attachmentMapper = BeanFactory.getInstance().getAttachmentMapper();
			this.vtemplatee = attachmentMapper.findById(this.vtemplate);
		}
		return vtemplatee;
	}

	public void setVtemplatee(Attachment vtemplatee) {
		this.vtemplatee = vtemplatee;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
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

	public List<ChannelContent> getChcontents() {
		if(this.chcontents ==null){
			ChannelContentMapper channelContentMapper = BeanFactory.getInstance().getChannelContentMapper();
			ChannelContent dc = new ChannelContent();
			dc.setChannel(id);
			this.chcontents = channelContentMapper.findByChannelContent(dc, null, null);
		}
		return this.chcontents;
	}

	public void setChcontents(List<ChannelContent> chcontents) {
		this.chcontents = chcontents;
	}

}
