package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Logistic;

public class LogisticUpdateAction 
extends BaseUploadAction
{
  private static final long serialVersionUID = -6907220288570571363L;
  private String ls;
  private Logistic logistic;
  
  public String execute()
    throws Exception
  {
    this.logistic = this.logisticService.findById(this.ls);
    return "success";
  }
  
  public String update()
    throws Exception
  {
    Logistic ul = this.logisticService.findById(this.logistic.getId());
    ul.setName(this.logistic.getName());
    ul.setIntro(this.logistic.getIntro());
    ul.setUrl(this.logistic.getUrl());
    ul.setPourl(this.logistic.getPourl());
    ul.setCsort(this.logistic.getCsort());
    Attachment attachment = upload();
    Attachment oimage = ul.getImagee();
    if (attachment != null) {
      ul.setImage(attachment.getId());
    }
    this.logisticService.update(ul);
    if ((attachment != null) && (oimage != null)) {
      this.attachmentService.delete(oimage.getId());
    }
    return "success";
  }
  
  public String getLs()
  {
    return this.ls;
  }
  
  public void setLs(String ls)
  {
    this.ls = ls;
  }
  
  public Logistic getLogistic()
  {
    return this.logistic;
  }
  
  public void setLogistic(Logistic logistic)
  {
    this.logistic= logistic;
  }
}

