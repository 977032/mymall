package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Channel;
import com.ffyc.server.utils.FileExt;

public class ChannelCreateAction 
extends BaseUploadAction
{
  private static final long serialVersionUID = -4504233842844019399L;
  private List tplist;
  private List localelist;
  private Channel channel = new Channel();
  private String localeid = "zh_CN";
  
  public String execute()
    throws Exception
  {
    this.localelist = this.localeService.findAll();
    this.tplist = this.attachmentService.findAllTemplate();
    return "success";
  }
  
  public String create()
    throws Exception
  {
    this.channel.setCtime(getTimestamp());
    this.channel.setManager(getManagerFromSession().getId());
    Attachment attachment = upload();
    if (attachment != null) {
      this.channel.setTemplate(attachment.getId());
    }
    this.channelService.save(this.channel);
    return "success";
  }
  
  public void setUploadFileName(String uploadFileName)
  {
    this.oldFileName = uploadFileName;
    setFextname(FileExt.getExtension(uploadFileName));
    this.langFileName = (this.channel.getId() + "." + FileExt.getExtension(uploadFileName));
    this.uploadFileName = (this.localeid + "_" + this.channel.getId() + "." + FileExt.getExtension(uploadFileName));
  }
  
  public void setSavePath(String savePath)
  {
    this.savePath = (savePath + "/custom");
  }
  
  public List getTplist()
  {
    return this.tplist;
  }
  
  public void setTplist(List tplist)
  {
    this.tplist = tplist;
  }
  
  public Channel getChannel()
  {
    return this.channel;
  }
  
  public void setChannel(Channel channel)
  {
    this.channel = channel;
  }
  
  public List getLocalelist()
  {
    return this.localelist;
  }
  
  public void setLocalelist(List localelist)
  {
    this.localelist = localelist;
  }
  
  public String getLocaleid()
  {
    return this.localeid;
  }
  
  public void setLocaleid(String localeid)
  {
    this.localeid = localeid;
  }
}
