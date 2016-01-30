package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import com.ffyc.server.mapper.ConfigMapper;
import com.ffyc.server.utils.BeanFactory;

public class Config implements Serializable {
	private static final long serialVersionUID = 8343010737354246119L;
	private String id;
	private Config config;
	private String pid;
	private String name;
	private String property;
	private String value;
	private String vtype;
	private String defaultvalue;
	private String optional;
	private String content;
	private Timestamp ctime;
	private Timestamp utime;
	private List configs;
	private String[] optionals;

	public Config() {
	}

	public String[] getOptionals() {
		if (StringUtils.isNotEmpty(this.optional)) {
			this.optionals = this.optional.split("[|]");
		}
		return this.optionals;
	}

	public void setOptionals(String[] optionals) {
		this.optionals = optionals;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Config getConfig() {
		if (StringUtils.isNotEmpty(this.pid) && this.config ==null){
			ConfigMapper configMapper = BeanFactory.getInstance().getConfigMapper();
			config= configMapper.findById(pid);
		}
		return this.config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProperty() {
		return this.property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getVtype() {
		return this.vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getDefaultvalue() {
		return this.defaultvalue;
	}

	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}

	public String getOptional() {
		return this.optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Timestamp getCtime() {
		return ctime;
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

	public List getConfigs() {
		if (this.configs ==null){
			ConfigMapper configMapper = BeanFactory.getInstance().getConfigMapper();
			List list= configMapper.findAllChild(id);
			if(list!=null){
				this.configs = list;
			}else{
			}
		}
		return this.configs;
	}

	public void setConfigs(List configs) {
		this.configs = configs;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

}
