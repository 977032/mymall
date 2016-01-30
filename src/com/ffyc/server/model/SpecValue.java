package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.mapper.SpecItemMapper;
import com.ffyc.server.mapper.SpecValueMapper;
import com.ffyc.server.mapper.StoreMapper;
import com.ffyc.server.utils.BeanFactory;

public class SpecValue implements Serializable {
	private String id;
	private String specitem;
	private SpecItem specitemm;
	private String image;
	private Attachment imagee;
	private Attachment vimage;
	private String name;
	private Timestamp ctime;
	private Timestamp utime;
	private Double price;
	private String manager;
	private Manager managerr;
	private List<Spec> specs;

	public SpecValue() {
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
		if (StringUtils.isNotEmpty(this.image) && this.imagee == null) {
			AttachmentMapper attachmentMapper = BeanFactory.getInstance()
					.getAttachmentMapper();
			this.imagee = attachmentMapper.findById(this.image);
		}
		return imagee;
	}

	public void setImagee(Attachment imagee) {
		this.imagee = imagee;
	}

	public Attachment getVimage() {
		if(this.imagee ==null) {
			getImagee();
		}
		if (this.imagee != null) {
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

	public List<Spec> getSpecs() {
		if(this.specs == null){
			SpecValueMapper specValueMapper = BeanFactory.getInstance().getSpecValueMapper();
			this.specs = specValueMapper.getSpecs(id);
		}
		return specs;
	}

	public void setSpecs(List<Spec> specs) {
		this.specs = specs;
	}

	public String getSpecitem() {
		return specitem;
	}

	public void setSpecitem(String specitem) {
		this.specitem = specitem;
	}

	public SpecItem getSpecitemm() {
		if (StringUtils.isNotEmpty(this.specitem) && this.specitemm == null) {
			SpecItemMapper specItemMapper = BeanFactory.getInstance()
					.getSpecItemMapper();
			this.specitemm = specItemMapper.findById(specitem);
		}
		return specitemm;
	}

	public void setSpecitemm(SpecItem specitemm) {
		this.specitemm = specitemm;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
	
}
