package com.ffyc.server.action.manager;

import java.io.PrintWriter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Config;

public class ConfigUpdateAjaxAction extends BaseAction {
	private static final long serialVersionUID = 133875309384869956L;
	private String cf;
	private Config config;
	private String optionallist;
	private JSONArray jo;
	private String newvalue;

	public String execute() throws Exception {
		try {
			Config conf = this.configService.findById(this.cf);
			conf.setValue(this.newvalue);
			this.configService.update(conf);
			PrintWriter out = this.response.getWriter();
			out.print(this.newvalue);
		} catch (NullPointerException localNullPointerException) {
		}
		return null;
	}

	public String getOptional() throws Exception {
		this.config = this.configService.findById(this.cf);
		String optional = this.config.getOptional();
		String[] ot = optional.split("[|]");

		JSONObject jj = JSONObject.fromObject(ot);
		System.out.println(jj);
		this.optionallist = jj.toString();
		return "success";
	}

	public String getCf() {
		return this.cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public Config getConfig() {
		return this.config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public String getOptionallist() {
		return this.optionallist;
	}

	public void setOptionallist(String optionallist) {
		this.optionallist = optionallist;
	}

	public JSONArray getJo() {
		return this.jo;
	}

	public void setJo(JSONArray jo) {
		this.jo = jo;
	}

	public String getNewvalue() {
		return this.newvalue;
	}

	public void setNewvalue(String newvalue) {
		this.newvalue = newvalue;
	}
}
