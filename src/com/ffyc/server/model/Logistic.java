package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AreaMapper;
import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.utils.BeanFactory;

public class Logistic implements Serializable {

	private String id;
	private String image;
	private Attachment imagee;
	private Attachment vimage;
	private String name;
	private String intro;
	private String url;
	private String pourl;
	private Integer csort;
	private List<Area> areas;

	public Logistic() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Attachment getImagee() {
		if(StringUtils.isNotEmpty(this.image) && this.imagee ==null){
			AttachmentMapper attachmentMapper = BeanFactory.getInstance().getAttachmentMapper();
			this.imagee = attachmentMapper.findById(image);
		}
		return imagee;
	}

	public void setImagee(Attachment imagee) {
		this.imagee = imagee;
	}

	public Attachment getVimage() {
		if (this.image != null) {
			return this.imagee;
		}
		Attachment i = new Attachment();
		i.setPath("/images/sys");
		i.setName("nopic.jpg");
		return i;
	}

	public void setVimage(Attachment vimage) {
		this.vimage = vimage;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPourl() {
		return this.pourl;
	}

	public void setPourl(String pourl) {
		this.pourl = pourl;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public List<Area> getAreas() {
		if(this.areas == null){
			AreaMapper areaMapper = BeanFactory.getInstance().getAreaMapper();
			Area dc= new Area();
			dc.setLogistic(id);
			this.areas = areaMapper.findByArea(dc, null, null);
		}
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}


}
