package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.DocumentMapper;
import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.utils.BeanFactory;

public class Attachment implements Serializable {
	private static final long serialVersionUID = -5702291605598783028L;

	public Attachment() {
	}

	private String id;
	private String manager;
	private Manager managerr;
	private String document;
	private Document documentt;
	private String good;
	private Good goodd;
	private String name;
	private String ftype;
	private String status;
	private Integer sort;
	private String path;
	private String oname;
	private String detail;
	private String url;
	private Timestamp ctime;
	private String vdate;
	private String vtime;
	
	public static final String STATUS_NORMAL = "normal";
	public static final String STATUS_DEFAULT = "default";

	public String getVdate() {
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
		sdf.applyPattern("yyyy-MM-dd");
		this.vdate = sdf.format(this.ctime);
		return this.vdate;
	}

	public String getVdate(String format) {
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getDateInstance();
		try {
			sdf.applyPattern(format);
			this.vdate = sdf.format(this.ctime);
		} catch (Exception e) {
			sdf.applyPattern("yyyy-MM-dd");
			this.vdate = sdf.format(this.ctime);
		}
		return this.vdate;
	}

	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

	public String getVtime() {
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getTimeInstance();
		sdf.applyPattern("HH:mm:ss");
		this.vtime = sdf.format(this.ctime);
		return this.vtime;
	}

	public String getVtime(String format) {
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getTimeInstance();
		try {
			sdf.applyPattern(format);
			this.vtime = sdf.format(this.ctime);
		} catch (Exception e) {
			sdf.applyPattern("HH:mm:ss");
			this.vtime = sdf.format(this.ctime);
		}
		return this.vtime;
	}

	public void setVtime(String vtime) {
		this.vtime = vtime;
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
		if(StringUtils.isNotEmpty(this.manager) && this.managerr ==null){
			ManagerMapper managerMapper = BeanFactory.getInstance().getManagerMapper();
			this.managerr = managerMapper.findById(this.manager);
		}
		return managerr;
	}

	public void setManagerr(Manager managerr) {
		this.managerr = managerr;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}
	
	public Document getDocumentt() {
		if(StringUtils.isNotEmpty(this.document) && this.documentt == null){
			DocumentMapper documentMapper = BeanFactory.getInstance().getDocumentMapper();
			this.documentt = documentMapper.findById(this.document);
		}
		return documentt;
	}

	public void setDocumentt(Document documentt) {
		this.documentt = documentt;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFtype() {
		return this.ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOname() {
		return this.oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}
