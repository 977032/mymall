package com.ffyc.server.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.utils.BeanFactory;
public class Brand implements Serializable {

	private String id;
	private String name;
	private String alias;
	private String logo;
	private Attachment logoo;
	private Attachment vlogo;
	private String url;
	private Integer csort;
	private String intro;

	public Brand() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return this.alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Attachment getLogoo() {
		if(StringUtils.isNotEmpty(this.logo) && this.logoo==null){
			AttachmentMapper attachmentMapper = BeanFactory.getInstance().getAttachmentMapper();
			this.logoo = attachmentMapper.findById(this.logo);
		}
		return logoo;
	}

	public void setLogoo(Attachment logoo) {
		this.logoo = logoo;
	}

	public Attachment getVlogo() {
		if (this.logoo != null) {
			return this.logoo;
		}
		Attachment i = new Attachment();
		i.setPath("/images/sys");
		i.setName("nopic.jpg");
		return i;
	}

	public void setVlogo(Attachment vlogo) {
		this.vlogo = vlogo;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

}
