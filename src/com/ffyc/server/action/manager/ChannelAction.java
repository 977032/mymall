package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.model.Channel;
import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.Locale;

public class ChannelAction
  extends BaseAction
{
  private static final long serialVersionUID = 7903954666905059306L;
  private List<Channel> list;
  private List<ChannelContent> gachctlist;
  private String[] checkbox;
  
  public String execute()
    throws Exception
  {
	
    this.list = this.channelService.findAll();
    this.gachctlist = this.channelContentService.findAllGlobal();
    this.channelContentService.getLocales(gachctlist);
    this.channelContentService.getDocumentCategorys(gachctlist);
    return "success";
  }
  
  public String delete()
    throws Exception
  {
    if ((this.checkbox != null) && (this.checkbox.length > 0)) {
      for (int i = 0; i < this.checkbox.length; i++)
      {
        Channel channel = this.channelService.findById(this.checkbox[i]);
        if (channel != null) {
          this.channelService.delete(channel.getId());
        }
      }
    }
    return "success";
  }
  
  public List getList()
  {
    return this.list;
  }
  
  public void setList(List list)
  {
    this.list = list;
  }
  
  public List getGachctlist()
  {
    return this.gachctlist;
  }
  
  public void setGachctlist(List gachctlist)
  {
    this.gachctlist = gachctlist;
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
