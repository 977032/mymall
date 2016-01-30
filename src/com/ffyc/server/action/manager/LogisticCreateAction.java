package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Logistic;

public class LogisticCreateAction 
extends BaseUploadAction
{
  private static final long serialVersionUID = -6123136668122513884L;
  private Logistic logistic = new Logistic();
  
  public String execute()
    throws Exception
  {
    return "success";
  }
  
  public String create()
    throws Exception
  {
    this.logistic.setId(getUuid());
    Attachment attachment = upload();
    if(attachment!=null){
    	this.logistic.setImage(attachment.getId());
    }
    this.logisticService.save(this.logistic);
    return "success";
  }
  
  public Logistic getLogistic()
  {
    return this.logistic;
  }
  
  public void setLogistic(Logistic logistic)
  {
    this.logistic = logistic;
  }
}
