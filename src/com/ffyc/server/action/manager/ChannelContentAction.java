package com.ffyc.server.action.manager;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.model.Channel;
import com.ffyc.server.model.ChannelContent;

public class ChannelContentAction 
extends BaseAction
{
  private static final long serialVersionUID = 1788395728394368111L;
  private String cid;
  private Channel channel;
  private List<ChannelContent> list;
  private String[] checkbox;
  
  public String execute()
    throws Exception
  {
    if (StringUtils.isNotEmpty(this.cid))
    {
      this.channel = this.channelService.findById(this.cid);
      if (this.channel != null)
      {
        ChannelContent dc = new ChannelContent();
        dc.setChannel(this.channel.getId());
        this.list = this.channelContentService.findByChannelContent(dc);
      }
    }
    return "success";
  }
  
  public String delete()
    throws Exception
  {
    boolean global = false;
    if ((this.checkbox != null) && (this.checkbox.length > 0)) {
    	for (int i = 0; i < this.checkbox.length; i++)
        {
          ChannelContent chcontent = this.channelContentService.findById(this.checkbox[i]);
          if (chcontent != null)
          {
            this.channelContentService.delete(chcontent.getId());
            if ((chcontent.getGlobal() != null) && (chcontent.getGlobal().equals("yes"))) {
              global = true;
            }
          }
        }
    }
    
    if (global) {
      return "global";
    }
    return "success";
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
  
  public List getList()
  {
    return this.list;
  }
  
  public void setList(List list)
  {
    this.list = list;
  }
  
  public String[] getCheckbox()
  {
    return this.checkbox;
  }
  
  public void setCheckbox(String[] checkbox)
  {
    this.checkbox = checkbox;
  }
}
