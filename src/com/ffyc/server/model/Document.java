package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.ChannelContentMapper;
import com.ffyc.server.mapper.DocumentCategoryMapper;
import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.utils.BeanFactory;
import com.ffyc.server.utils.SubString;

public class Document implements Serializable {
	public Document() {
	}

	private String id;
	private String manager;
	private Manager managerr;
	private String image;
	private Attachment imagee;
	private Attachment vimagee;
	private String locale;
	private Locale localee;
	private String doccate;
	private DocumentCategory doccatee;
	private String title;
	private String brief;
	private String subrief;
	private String content;
	private String marker;
	private Integer vistor;
	private Integer csort;
	private Timestamp ctime;
	private Timestamp utime;
	private List<ChannelContent> chcontents;
	private List<Attachment> attachments;
	private String subtitle;
	private Attachment defaultimage;
	private String vdate;
	private String vtime;
	
	public final static String MARKER_RECOMMAND= "recommand";

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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	public Manager getManagerr() {
		if(StringUtils.isNotEmpty(this.manager) && this.managerr==null){
			ManagerMapper managerMapper = BeanFactory.getInstance().getManagerMapper();
			this.managerr = managerMapper.findById(this.manager);
		}
		return managerr;
	}

	public void setManagerr(Manager managerr) {
		this.managerr = managerr;
	}

	public String getSubtitle() {
		return this.subtitle;
	}

	public String getSubtitle(int n, String ext) {
		this.subtitle = SubString.substring(this.title, n, ext);
		return this.subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
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
			this.imagee = attachmentMapper.findById(this.image);
		}
		return this.imagee;
	}

	public void setImagee(Attachment imagee) {
		this.imagee = imagee;
	}

	public Attachment getVimagee() {

		if (this.getImagee() != null) {
			return this.imagee;
		}
		Attachment i = new Attachment();
		i.setPath("/images/sys");
		i.setName("nopic.jpg");
		return i;
	}

	public void setVimagee(Attachment vimagee) {
		this.vimagee = vimagee;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Locale getLocalee() {
		if(StringUtils.isNotEmpty(this.locale) && this.localee == null){
			LocaleMapper localeMapper = BeanFactory.getInstance().getLocaleMapper();
			this.localee = localeMapper.findById(this.locale);
		}
		return this.localee;
	}

	public void setLocalee(Locale localee) {
		this.localee = localee;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return this.brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getSubrief() {
		return this.subrief;
	}

	public String getSubrief(int n, String ext) {
		this.subrief = SubString.substring(this.brief, n, ext);
		return this.subrief;
	}

	public void setSubrief(String subrief) {
		this.subrief = subrief;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMarker() {
		return this.marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public Integer getVistor() {
		return this.vistor;
	}

	public void setVistor(Integer vistor) {
		this.vistor = vistor;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public Timestamp getCtime() {
		return this.ctime;
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
	
	public String getDoccate() {
		return doccate;
	}

	public void setDoccate(String doccate) {
		this.doccate = doccate;
	}

	public DocumentCategory getDoccatee() {
		if(StringUtils.isNotEmpty(this.doccate) && this.doccatee ==null){
			DocumentCategoryMapper documentCategoryMapper = BeanFactory.getInstance().getDocumentCategoryMapper();
			this.doccatee = documentCategoryMapper.findById(this.doccate);
		}
		return doccatee;
	}

	public void setDoccatee(DocumentCategory doccatee) {
		this.doccatee = doccatee;
	}

	public List<ChannelContent> getChcontents() {
		if(this.chcontents == null){
			ChannelContentMapper channelContentMapper = BeanFactory.getInstance().getChannelContentMapper();
			ChannelContent dc = new ChannelContent();
			dc.setDocument(this.id);
			this.chcontents = channelContentMapper.findByChannelContent(dc, null, null);
		}
		return chcontents;
	}

	public void setChcontents(List<ChannelContent> chcontents) {
		this.chcontents = chcontents;
	}

	public Attachment getDefaultimage() {
		return defaultimage;
	}

	public void setDefaultimage(Attachment defaultimage) {
		this.defaultimage = defaultimage;
	}

	public List<Attachment> getAttachments() {
		if(this.attachments ==null){
			AttachmentMapper attachmentMapper= BeanFactory.getInstance().getAttachmentMapper();
			Attachment dc = new Attachment();
			dc.setDocument(this.getId());
			this.attachments = attachmentMapper.findByAttachment(dc,null,null);
		}
		return this.attachments;
	}

	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;

		Iterator it = attachments.iterator();
		while (it.hasNext()) {
			Attachment attach = (Attachment) it.next();
			if (attach.getStatus().equals("default")) {
				this.defaultimage = attach;
				break;
			}
		}
	}
}
