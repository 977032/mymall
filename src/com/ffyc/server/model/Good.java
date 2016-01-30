package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.BrandMapper;
import com.ffyc.server.mapper.CategoryMapper;
import com.ffyc.server.mapper.ChannelContentMapper;
import com.ffyc.server.mapper.CommentMapper;
import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.GoodTypeMapper;
import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.mapper.OrderItemMapper;
import com.ffyc.server.mapper.SpecMapper;
import com.ffyc.server.utils.BeanFactory;
import com.ffyc.server.utils.SubString;

public class Good implements Serializable {

	private String id;
	private String manager;
	private Manager managerr;
	private String goodtype;
	private GoodType goodtypee;
	private String locale;
	private Locale localee;
	private String category;
	private Category categoryy;
	private String brand;
	private Brand brandd;
	private String name;
	private String sn;
	private String barcode;
	private String keyword;
	private String intro;
	private String detail;
	private Double price;
	private Double mprice;
	private Double cprice;
	private Double weight;
	private Integer wgunit;
	private String freeshipping;
	private Integer points;
	private Integer inventory;
	private String promote;
	private Timestamp ctime;
	private Timestamp utime;
	private String status;
	private Integer csort;
	private String mkeyword;
	private String mdescription;
	private Integer vistor;
	private List<Spec> specs;
	private List<Attachment> attachments;
	private List<Comment> comments;
	private List<ExtraAttrValue> extraattrvalues;
	private List<OrderItem> orderitems;
	private List<ChannelContent> chcontents;
	private String subname;
	private String subintro;
	private Integer trans;
	private Attachment defaultimage;
	private String pricescope;
	private String[] keys;
	private String vdate;
	private String vtime;
	
	public final static String STATUS_NORMAL= "normal";
	public final static String STATUS_RECOMMAND= "recommand";

	public Good() {
	}

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

	public Attachment getDefaultimage() {
		if (this.defaultimage != null) {
			return this.defaultimage;
		}
		Attachment image = new Attachment();
		image.setPath("/images");
		image.setName("nopic.jpg");
		return image;
	}

	public void setDefaultimage(Attachment defaultimage) {
		this.defaultimage = defaultimage;
	}

	public String getPricescope() {
		if(this.specs == null){
			this.getSpecs();
		}
		Iterator it = this.specs.iterator();
		List<Double> al = new ArrayList();
		while (it.hasNext()) {
			Spec spec = (Spec) it.next();
			al.add(spec.getPrice());
		}
		DecimalFormat format = (DecimalFormat) NumberFormat.getInstance();
		format.applyPattern("0.00");
		if (al.size() > 0) {
			this.pricescope = (format.format(Collections.min(al)) + "-" + format
					.format(Collections.max(al)));
		} else {
			try {
				this.pricescope = this.price.toString();
			} catch (NullPointerException e) {
				this.pricescope = null;
			}
		}
		return this.pricescope;
	}

	public void setPricescope(String pricescope) {
		this.pricescope = pricescope;
	}

	public Integer getTrans() {
		if(this.orderitems ==null){
			this.getOrderitems();
		}
		if(this.orderitems !=null){
			Integer number = Integer.valueOf(0);
			Iterator it = orderitems.iterator();
			while (it.hasNext()) {
				OrderItem oi = (OrderItem) it.next();
			    number = Integer.valueOf(number.intValue() +
				oi.getNumber().intValue());
			}
			this.trans = number;
		}

		return this.trans;
	}

	public void setTrans(Integer trans) {
		this.trans = trans;
	}

	public String getSubname() {
		return this.subname;
	}

	public String getSubname(int n, String ext) {
		this.subname = SubString.substring(this.name, n, ext);
		return this.subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}

	public String getSubintro() {
		return this.subintro;
	}

	public String getSubintro(int n, String ext) {
		this.subintro = SubString.substring(this.intro, n, ext);
		return this.subintro;
	}

	public void setSubintro(String subintro) {
		this.subintro = subintro;
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
		if (StringUtils.isNotEmpty(this.manager) && this.managerr == null) {
			ManagerMapper managerMapper = BeanFactory.getInstance()
					.getManagerMapper();
			this.managerr = managerMapper.findById(this.manager);
		}
		return managerr;
	}

	public void setManagerr(Manager managerr) {
		this.managerr = managerr;
	}

	public String getGoodtype() {
		return goodtype;
	}

	public void setGoodtype(String goodtype) {
		this.goodtype = goodtype;
	}

	public GoodType getGoodtypee() {
		if(StringUtils.isNotEmpty(this.goodtype) && this.goodtypee == null){
			GoodTypeMapper goodTypeMapper = BeanFactory.getInstance().getGoodTypeMapper();
			this.goodtypee = goodTypeMapper.findById(this.goodtype);
		}
		return goodtypee;
	}

	public void setGoodtypee(GoodType goodtypee) {
		this.goodtypee = goodtypee;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Locale getLocalee() {
		if(StringUtils.isNotEmpty(this.locale) && this.localee==null){
			LocaleMapper localeMapper = BeanFactory.getInstance().getLocaleMapper();
			this.localee = localeMapper.findById(this.locale);
		}
		return localee;
	}

	public void setLocalee(Locale localee) {
		this.localee = localee;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Category getCategoryy() {
		if(StringUtils.isNotEmpty(this.category) && this.categoryy==null){
			CategoryMapper categoryMapper = BeanFactory.getInstance().getCategoryMapper();
			this.categoryy = categoryMapper.findById(this.category);
		}
		return categoryy;
	}

	public void setCategoryy(Category categoryy) {
		this.categoryy = categoryy;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Brand getBrandd() {
		if(StringUtils.isNotEmpty(this.brand) && this.brandd== null){
			BrandMapper brandMapper = BeanFactory.getInstance().getBrandMapper();
			this.brandd = brandMapper.findById(this.brand);
		}
		return brandd;
	}

	public void setBrandd(Brand brandd) {
		this.brandd = brandd;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return this.sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
		if (keyword != null) {
			this.keys = keyword.split(",");
		}
	}

	public boolean hasKeyword(String keyword) {
		if (this.keyword != null) {
			String[] list = this.keyword.split(",");
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					String key = list[i];
					if (keyword.equals(key)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getMprice() {
		return this.mprice;
	}

	public void setMprice(Double mprice) {
		this.mprice = mprice;
	}

	public Double getCprice() {
		return this.cprice;
	}

	public void setCprice(Double cprice) {
		this.cprice = cprice;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		if (weight != null) {
			this.weight = weight;
		} else {
			this.weight = new Double(0.0D);
		}
		Double w = weight;
		if ((this.wgunit != null) && (this.wgunit.intValue() > 0)) {
			w = Double.valueOf(weight.doubleValue() * this.wgunit.intValue());
		}
		this.weight = w;
	}

	public Integer getWgunit() {
		return this.wgunit;
	}

	public void setWgunit(Integer wgunit) {
		this.wgunit = wgunit;
	}

	public String getFreeshipping() {
		return this.freeshipping;
	}

	public void setFreeshipping(String freeshipping) {
		this.freeshipping = freeshipping;
	}

	public Integer getPoints() {
		return this.points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getInventory() {
		return this.inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public String getPromote() {
		return this.promote;
	}

	public void setPromote(String promote) {
		this.promote = promote;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public Timestamp getUtime() {
		return this.utime;
	}

	public void setUtime(Timestamp utime) {
		this.utime = utime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getVistor() {
		return this.vistor;
	}

	public void setVistor(Integer vistor) {
		this.vistor = vistor;
	}

	public List<Spec> getSpecs() {
		if(this.specs == null){
			SpecMapper specMapper = BeanFactory.getInstance().getSpecMapper();
			Spec dc = new Spec();
			dc.setGood(id);
			this.specs = specMapper.findBySpec(dc, null, null);
		}
		return this.specs;
	}

	public void setSpecs(List<Spec> specs) {
		this.specs = specs;
	}

	public List<Attachment> getAttachments() {
		if(this.attachments == null){
			AttachmentMapper attachmentMapper = BeanFactory.getInstance().getAttachmentMapper();
			Attachment dc = new Attachment();
			dc.setGood(id);
			this.attachments = attachmentMapper.findByAttachment(dc, null, null);
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

	public List<Comment> getComments() {
		if(this.comments == null){
			CommentMapper commentMapper = BeanFactory.getInstance().getCommentMapper();
			Comment dc = new Comment();
			dc.setGood(id);
			this.comments = commentMapper.findByComment(dc, null, null);
		}
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<ExtraAttrValue> getExtraattrvalues() {
		if(this.extraattrvalues == null){
			GoodMapper goodMapper = BeanFactory.getInstance().getGoodMapper();
			this.extraattrvalues = goodMapper.getExtraAttrValues(id);
		}
		return extraattrvalues;
	}

	public void setExtraattrvalues(List<ExtraAttrValue> extraattrvalues) {
		this.extraattrvalues = extraattrvalues;
	}

	public List<OrderItem> getOrderitems() {
		if(this.orderitems == null){
			OrderItemMapper orderItemMapper = BeanFactory.getInstance().getOrderItemMapper();
			OrderItem dc = new OrderItem();
			dc.setGood(id);
			this.orderitems = orderItemMapper.findByOrderItem(dc, null, null);
		}
		return this.orderitems;
	}

	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}

	public List<ChannelContent> getChcontents() {
		if(this.chcontents == null){
			ChannelContentMapper channelContentMapper = BeanFactory.getInstance().getChannelContentMapper();
			ChannelContent dc = new ChannelContent();
			dc.setGood(id);
			this.chcontents = channelContentMapper.findByChannelContent(dc, null, null);
		}
		return this.chcontents;
	}

	public void setChcontents(List<ChannelContent> chcontents) {
		this.chcontents = chcontents;
	}

	public String[] getKeys() {
		return this.keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
		this.keys = keys;
		if (keys != null) {
			String kws = "";
			for (int i = 0; i < keys.length; i++) {
				kws = kws + keys[i] + ",";
			}
			this.keyword = kws;
		} else {
			this.keyword = null;
		}
	}
	
	public void insertExtraAttrValues() {
		GoodMapper goodMapper = BeanFactory.getInstance()
				.getGoodMapper();
		if(this.extraattrvalues!=null){
			goodMapper.insertExtraAttrValues(id, this.extraattrvalues);
		}
	}
	
	public void deleteExtraAttrValues() {
		GoodMapper goodMapper = BeanFactory.getInstance()
				.getGoodMapper();
		if(this.id!=null){
			goodMapper.deleteExtraAttrValues(id);
		}
	}
	
	
}
