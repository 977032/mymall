package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.CategoryMapper;
import com.ffyc.server.mapper.ChannelContentMapper;
import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.GoodTypeMapper;
import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.utils.BeanFactory;

public class Category implements Serializable {

	private String id;
	private Attachment imagee;
	private String image;
	private Attachment vimage;
	private GoodType goodtypee;
	private String goodtype;
	private Locale localee;
	private String locale;
	private Category category;
	private String pid;
	private String nodepath;
	private String nodetype;
	private String name;
	private String detail;
	private Integer csort;
	private String mkeyword;
	private String mdescription;
	private List<Good> goods;
	private List<Category> categories;
	private List<ChannelContent> chcontents;
	private String nodestr;

	public Category() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Attachment getImagee() {
		if(StringUtils.isNotEmpty(this.image) && this.imagee ==null){
			AttachmentMapper attachmentMapper = BeanFactory.getInstance().getAttachmentMapper();
			this.imagee = attachmentMapper.findById(this.image);
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

	public GoodType getGoodtypee() {
		if(StringUtils.isNotEmpty(this.goodtype) && this.goodtypee==null){
			GoodTypeMapper goodTypeMapper = BeanFactory.getInstance().getGoodTypeMapper();
			this.goodtypee = goodTypeMapper.findById(this.goodtype);
		}
		return goodtypee;
	}

	public void setGoodtypee(GoodType goodtypee) {
		this.goodtypee = goodtypee;
	}

	public String getGoodtype() {
		return goodtype;
	}
	
	public void setGoodtype(String goodtype) {
		this.goodtype = goodtype;
	}
	
	public Category getCategory() {
		if(StringUtils.isNotEmpty(this.pid) && this.category == null){
			CategoryMapper categoryMapper = BeanFactory.getInstance().getCategoryMapper();
			this.category = categoryMapper.findById(this.pid);
		}
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Locale getLocalee() {
		if(StringUtils.isNotEmpty(this.locale) && this.localee == null){
			LocaleMapper localeMapper = BeanFactory.getInstance().getLocaleMapper();
			this.localee = localeMapper.findById(this.locale);
		}
		return localee;
	}

	public void setLocalee(Locale localee) {
		this.localee = localee;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getNodepath() {
		return this.nodepath;
	}

	public void setNodepath(String nodepath) {
		this.nodepath = nodepath;
	}

	public String getNodetype() {
		return this.nodetype;
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

	public List<Good> getGoods() {
		if(this.goods == null){
			GoodMapper goodMapper = BeanFactory.getInstance().getGoodMapper();
			Good dc = new Good();
			dc.setCategory(id);
			this.goods = goodMapper.findByGood(dc, null, null);
		}
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}

	public List<Category> getCategories() {
		if(this.categories == null){
			CategoryMapper categoryMapper = BeanFactory.getInstance().getCategoryMapper();
			Category dc = new Category();
			dc.setPid(id);
			this.categories = categoryMapper.findByCategory(dc, null, null);
		}
		return this.categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<ChannelContent> getChcontents() {
		if(this.chcontents == null){
			ChannelContentMapper channelContentMapper = BeanFactory.getInstance().getChannelContentMapper();
			ChannelContent dc = new ChannelContent();
			dc.setCategory(id);
			this.chcontents = channelContentMapper.findByChannelContent(dc, null, null);
		}
		return this.chcontents;
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
