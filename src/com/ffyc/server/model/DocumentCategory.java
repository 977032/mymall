package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.ChannelContentMapper;
import com.ffyc.server.mapper.DocumentCategoryMapper;
import com.ffyc.server.mapper.DocumentMapper;
import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.utils.BeanFactory;

public class DocumentCategory implements Serializable {
	public DocumentCategory() {
	}

	private String id;
	private Attachment imagee;
	private String image;
	private Attachment vimage;
	private Locale localee;
	private String locale;
	private DocumentCategory doccate;
	private String pid;
	private String nodepath;
	private String nodetype;
	private String name;
	private String detail;
	private Integer csort;
	private String mkeyword;
	private String mdescription;
	private List<Document> documents;
	private List<DocumentCategory> doccates;
	private List<ChannelContent> chcontents;
	
	private String nodestr;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Attachment getImagee() {
		if(StringUtils.isNotEmpty(this.image) && this.imagee==null){
			AttachmentMapper attachmentMapper = BeanFactory.getInstance().getAttachmentMapper();
			this.imagee = attachmentMapper.findById(image);
		}
		return imagee;
	}

	public void setImagee(Attachment imagee) {
		this.imagee = imagee;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Attachment getVimage() {
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Locale getLocalee() {
		if(StringUtils.isNotEmpty(this.locale) && this.localee ==null){
			LocaleMapper localeMapper = BeanFactory.getInstance().getLocaleMapper();
			this.localee = localeMapper.findById(this.locale);
		}
		return localee;
	}

	public void setLocalee(Locale localee) {
		this.localee = localee;
	}


	public DocumentCategory getDoccate() {
		if(StringUtils.isNotEmpty(this.pid) && this.doccate==null){
			DocumentCategoryMapper documentCategoryMapper = BeanFactory.getInstance().getDocumentCategoryMapper();
			this.doccate= documentCategoryMapper.findById(pid);
		}
		return doccate;
	}

	public void setDoccate(DocumentCategory doccate) {
		this.doccate = doccate;
	}

	public String getNodepath() {
		return nodepath;
	}

	public void setNodepath(String nodepath) {
		this.nodepath = nodepath;
	}

	public String getNodetype() {
		return nodetype;
	}

	public void setNodetype(String nodetype) {
		this.nodetype = nodetype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public String getMkeyword() {
		return this.mkeyword;
	}

	public void setMkeyword(String mkeyword) {
		this.mkeyword = mkeyword;
	}

	public String getMdescription() {
		return this.mdescription;
	}

	public void setMdescription(String mdescription) {
		this.mdescription = mdescription;
	}

	public List<Document> getDocuments() {
		if(this.documents == null){
			DocumentMapper documentMapper = BeanFactory.getInstance().getDocumentMapper();
			Document dc = new Document();
			dc.setDoccate(id);
			this.documents = documentMapper.findByDocument(dc, null, null);
		}
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<DocumentCategory> getDoccates() {
		if(this.doccates == null){
			DocumentCategoryMapper documentCategoryMapper = BeanFactory.getInstance().getDocumentCategoryMapper();
			DocumentCategory dc = new DocumentCategory();
			dc.setPid(id);
			this.doccates = documentCategoryMapper.findByDocumentCategory(dc);
		}
		return doccates;
	}

	public void setDoccates(List<DocumentCategory> doccates) {
		this.doccates = doccates;
	}

	public List<ChannelContent> getChcontents() {
		if(this.chcontents == null){
			ChannelContentMapper channelContentMapper = BeanFactory.getInstance().getChannelContentMapper();
			ChannelContent dc = new ChannelContent();
			dc.setDoccate(id);
			this.chcontents =channelContentMapper.findByChannelContent(dc, null, null);
		}
		return chcontents;
	}

	public void setChcontents(List<ChannelContent> chcontents) {
		this.chcontents = chcontents;
	}

	public String getNodestr() {
		return this.nodestr;
	}

	public void setNodestr(String nodestr) {
		this.nodestr = nodestr;
	}
}
