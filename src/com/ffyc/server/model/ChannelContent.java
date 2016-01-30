package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.CategoryMapper;
import com.ffyc.server.mapper.ChannelMapper;
import com.ffyc.server.mapper.DocumentCategoryMapper;
import com.ffyc.server.mapper.DocumentMapper;
import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.utils.BeanFactory;

public class ChannelContent implements Serializable {
	private static final long serialVersionUID = -7453611558373972723L;
	private String id;
	private String document;
	private Document documentt;
	private String manager;
	private Manager managerr;
	private String channel;
	private Channel channell;
	private String locale;
	private Locale localee;
	private String doccate;
	private DocumentCategory doccatee;
	private String category;
	private Category categoryy;
	private String good;
	private Good goodd;
	private Integer number;
	private Integer offset;
	private String marker;
	private String global;
	private String type;
	private String keyword;
	private String detail;
	private Timestamp ctime;
	private Timestamp utime;
	private String typetext;
	private String[] keys;
	
    //内容类型
    public static final String TYPE_DMENU = "dmenu"; //文章分类
	public static final String TYPE_DLIST = "dlist"; //文章列表
	public static final String TYPE_DRCLIST = "drclist"; //推荐文章列表
	public static final String TYPE_DPAGELIST = "dpagelist";//分页文章列表
	
	public static final String TYPE_GMENU = "gmenu";//商品分类
	public static final String TYPE_GLIST = "glist";//商品列表
	public static final String TYPE_GRCLIST = "grclist"; //推荐商品列表
	public static final String TYPE_GPAGELIST= "gpagelist";//分页商品列表
	
	public static final String TYPE_DSINGLE = "dsingle"; //单条信息

	public ChannelContent() {
	}

	public String getTypetext() {
		return this.typetext;
	}

	public void setTypetext(String typetext) {
		this.typetext = typetext;
	}

	public String[] getKeys() {
		return this.keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
		if (keys != null) {
			String kw = "";
			for (int i = 0; i < keys.length; i++) {
				kw = kw + keys[i] + ",";
			}
			this.keyword = kw;
		} else {
			this.keyword = null;
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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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


	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Channel getChannell() {
		if(StringUtils.isNotEmpty(this.channel) && this.channell == null){
			ChannelMapper channelMapper = BeanFactory.getInstance().getChannelMapper();
			this.channell = channelMapper.findById(this.channel);
		}
		return channell;
	}

	public void setChannell(Channel channell) {
		this.channell = channell;
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
		return localee;
	}

	public void setLocalee(Locale localee) {
		this.localee = localee;
	}

	public String getDoccate() {
		return doccate;
	}

	public void setDoccate(String doccate) {
		this.doccate = doccate;
	}

	public DocumentCategory getDoccatee() {
		if(StringUtils.isNotEmpty(this.doccate) && this.doccatee == null){
			DocumentCategoryMapper documentCategoryMapper = BeanFactory.getInstance().getDocumentCategoryMapper();
			this.doccatee = documentCategoryMapper.findById(this.doccate);
		}
		return doccatee;
	}

	public void setDoccatee(DocumentCategory doccatee) {
		this.doccatee = doccatee;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Category getCategoryy() {
		if(StringUtils.isNotEmpty(this.category) && this.categoryy == null){
			CategoryMapper categoryMapper = BeanFactory.getInstance().getCategoryMapper();
			this.categoryy = categoryMapper.findById(this.category);
		}
		return categoryy;
	}

	public void setCategoryy(Category categoryy) {
		this.categoryy = categoryy;
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

	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getOffset() {
		return this.offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getMarker() {
		return this.marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public String getGlobal() {
		return this.global;
	}

	public void setGlobal(String global) {
		if ((global == null) || (global.equals(""))
				|| ((!global.equals("yes")) && (!global.equals("no")))) {
			global = "no";
		}
		this.global = global;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
		if (type.equals(TYPE_DMENU)) {
			this.typetext = "文章分类";
		} else if (type.equals(TYPE_DLIST)) {
			this.typetext = "文章列表";
		} else if (type.equals(TYPE_DRCLIST)) {
			this.typetext = "推荐文章列表";
		} else if (type.equals(TYPE_DPAGELIST)) {
			this.typetext = "分页文章列表";
		} else if (type.equals(TYPE_GMENU)) {
			this.typetext = "商品分类";
		} else if (type.equals(TYPE_GLIST)) {
			this.typetext = "商品列表";
		} else if (type.equals(TYPE_GRCLIST)) {
			this.typetext = "推荐商品列表";
		} else if (type.equals(TYPE_GPAGELIST)) {
			this.typetext = "分页商品列表";
		} else if (type.equals(TYPE_DSINGLE)) {
			this.typetext = "单条信息";
		}
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

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
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
}
