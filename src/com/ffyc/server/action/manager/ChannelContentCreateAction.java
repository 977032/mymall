package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Channel;
import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.Document;

public class ChannelContentCreateAction 
extends BaseAction
{
  private static final long serialVersionUID = 3399977905649826918L;
  private String cid;
  private Channel channel;
  private List doccates;
  private List categorys;
  private List localelist;
  private List channels;
  private String d;
  private Document document;
  private ChannelContent chcontent = new ChannelContent();
  
  public String execute()
    throws Exception
  {
    this.channel = this.channelService.findById(this.cid);
    this.doccates = this.documentCategoryService.findAllTree();
    this.categorys = this.categoryService.findAllTree();
    this.localelist = this.localeService.findAll();
    return "success";
  }
  
  public String doc_input()
    throws Exception
  {
    this.channels = this.channelService.findAll();
    this.document = this.documentService.findById(this.d);
    this.localelist = this.localeService.findAll();
    return "success";
  }
  
  public String global_input()
    throws Exception
  {
    this.doccates = this.documentCategoryService.findAllTree();
    this.categorys = this.categoryService.findAllTree();
    this.localelist = this.localeService.findAll();
    return "success";
  }
  
  public String create()
    throws Exception
  {
    String returnstr = "success";
    if (StringUtils.isNotEmpty(this.cid))
    {
      this.channel = this.channelService.findById(this.cid);
      if (this.channel != null)
      {
        this.chcontent.setChannel(this.channel.getId());
      }
      else if (this.chcontent.getGlobal().equals("no"))
      {
        this.notice.setStatus(false);
        this.notice.setCode("Chcontent Set Error");
        this.notice.setTitle("错误");
        this.notice.setBody("该频道内容既不属于任何频道，也不是全局频道内容，请检查后重新操作。");
        return "error";
      }
    }
    else
    {
      this.chcontent.setChannel(null);
      returnstr = "channels";
    }
    this.chcontent.setId(getUuid());
    this.chcontent.setCtime(getTimestamp());
    this.chcontent.setManager(getManagerFromSession().getId());
    if ((this.chcontent.getCategoryy().getId() == null) || (this.chcontent.getCategoryy().getId().equals(""))) {
      this.chcontent.setCategory(null);
    }
    if ((this.chcontent.getDoccatee().getId() == null) || (this.chcontent.getDoccatee().getId().equals(""))) {
      this.chcontent.setDoccate(null);
    }
    if ((this.chcontent.getDocumentt().getId() == null) || (this.chcontent.getDocumentt().getId().equals(""))) {
      this.chcontent.setDocument(null);
    }
    try{
    	this.channelContentService.save(this.chcontent);
    }catch(Exception e){
    	e.printStackTrace();
    }
    
    return returnstr;
  }
  
  public String getCid()
  {
    return this.cid;
  }
  
  public void setCid(String cid)
  {
    this.cid = cid;
  }
  
  public Channel getChannel()
  {
    return this.channel;
  }
  
  public void setChannel(Channel channel)
  {
    this.channel = channel;
  }
  
  public ChannelContent getChcontent()
  {
    return this.chcontent;
  }
  
  public void setChcontent(ChannelContent chcontent)
  {
    this.chcontent = chcontent;
  }
  
  public List getDoccates()
  {
    return this.doccates;
  }
  
  public void setDoccates(List doccates)
  {
    this.doccates = doccates;
  }
  
  public List getCategorys()
  {
    return this.categorys;
  }
  
  public void setCategorys(List categorys)
  {
    this.categorys = categorys;
  }
  
  public List getLocalelist()
  {
    return this.localelist;
  }
  
  public void setLocalelist(List localelist)
  {
    this.localelist = localelist;
  }
  
  public List getChannels()
  {
    return this.channels;
  }
  
  public void setChannels(List channels)
  {
    this.channels = channels;
  }
  
  public String getD()
  {
    return this.d;
  }
  
  public void setD(String d)
  {
    this.d = d;
  }
  
  public Document getDocument()
  {
    return this.document;
  }
  
  public void setDocument(Document document)
  {
    this.document = document;
  }
}
