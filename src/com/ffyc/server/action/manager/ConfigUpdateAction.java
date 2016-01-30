package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Config;

public class ConfigUpdateAction 
extends BaseAction
{
  private static final long serialVersionUID = 4506943983883024077L;
  private List configroot;
  private String cf;
  private Config config;
  
  public String execute()
    throws Exception
  {
    this.configroot = this.configService.findAllRoot();
    this.config = this.configService.findById(this.cf);
    return "success";
  }
  
  public String update()
    throws Exception
  {
    Config ucf = this.configService.findById(this.config.getId());
    if ((this.config.getConfig() != null) && (StringUtils.isNotEmpty(this.config.getConfig().getId()))) {
      ucf.setConfig(this.config.getConfig());
    } else {
      ucf.setConfig(null);
    }
    ucf.setName(this.config.getName());
    ucf.setProperty(this.config.getProperty());
    ucf.setValue(this.config.getValue());
    ucf.setContent(this.config.getContent());
    ucf.setUtime(getTimestamp());
    this.configService.update(ucf);
    return "success";
  }
  
  public String doset()
    throws Exception
  {
    Config ucf = this.configService.findById(this.config.getId());
    ucf.setValue(this.config.getValue());
    ucf.setUtime(getTimestamp());
    this.configService.update(ucf);
    return "success";
  }
  
  public List getConfigroot()
  {
    return this.configroot;
  }
  
  public void setConfigroot(List configroot)
  {
    this.configroot = configroot;
  }
  
  public String getCf()
  {
    return this.cf;
  }
  
  public void setCf(String cf)
  {
    this.cf = cf;
  }
  
  public Config getConfig()
  {
    return this.config;
  }
  
  public void setConfig(Config config)
  {
    this.config = config;
  }
}
