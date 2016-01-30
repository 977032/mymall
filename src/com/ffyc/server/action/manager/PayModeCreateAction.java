package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseUploadAction;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.PayMode;

public class PayModeCreateAction 
extends BaseUploadAction
{
  private static final long serialVersionUID = -1719513740257862169L;
  private PayMode paymode = new PayMode();
  
  public String execute()
    throws Exception
  {
    return "success";
  }
  
	public String create() throws Exception {
		this.paymode.setId(getUuid());
		Attachment attachment = upload();
		if(attachment!=null){
			this.paymode.setImage(attachment.getId());
		}
		try {
			this.payModeService.save(this.paymode);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

		return "success";
	}
  
  public PayMode getPaymode()
  {
    return this.paymode;
  }
  
  public void setPaymode(PayMode paymode)
  {
    this.paymode = paymode;
  }
}
