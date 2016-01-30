package com.ffyc.server.action.manager;
import java.util.List;

import com.ffyc.server.dc.PaySettingDC;
import com.ffyc.server.model.PaySetting;
public class PaySettingCreateAction 
extends BaseAction
{
  private static final long serialVersionUID = 818583751137624800L;
  private List<PaySetting> list;
  private PaySetting paysetting = new PaySetting();
  
  public String execute()
    throws Exception
  {
	PaySettingDC dc = new PaySettingDC();
	dc.setPaymode("null");
	dc.setOrderby("order by pmtype asc ");
	this.list = this.paySettingService.findByPaySetting(dc);
    return "success";
  }
  
  public String create()
    throws Exception
  {
    this.paysetting.setId(getUuid());
    this.paySettingService.save(this.paysetting);
    return "success";
  }
  
  public List<PaySetting> getList()
  {
    return this.list;
  }
  
  public void setList(List<PaySetting> list)
  {
    this.list = list;
  }
  
  public PaySetting getPaysetting()
  {
    return this.paysetting;
  }
  
  public void setPaysetting(PaySetting paysetting)
  {
    this.paysetting = paysetting;
  }
}
