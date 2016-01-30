package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.dc.PaySettingDC;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.model.PaySetting;

public class PayModeUpdateAction 
extends BaseUploadAction
{
  private static final long serialVersionUID = -5738827243048403341L;
  private String pm;
  private PayMode paymode;
  private List list;
  private List<PaySetting> paysettings;
  
  public String execute()
    throws Exception
  {
    this.paymode = this.payModeService.findById(this.pm);
    PaySettingDC dc = new PaySettingDC();
    dc.setPaymode(this.paymode.getId());
    this.list = this.paySettingService.findByPaySetting(dc);
    List root = this.paySettingService.findAllRoot(this.paymode.getPmtype());
    for (int i = 0; i < root.size(); i++)
    {
      PaySetting setting = (PaySetting)root.get(i);
      boolean flag = false;
      for (int j = 0; j < this.list.size(); j++)
      {
        PaySetting sett = (PaySetting)this.list.get(j);
        if (sett.getProperty().equals(setting.getProperty()))
        {
          flag = true;
          break;
        }
      }
      if (!flag) {
        this.list.add(setting);
      }
    }
    return "success";
  }
  
  public String update()
    throws Exception
  {
    PayMode upm = this.payModeService.findById(this.paymode.getId());
    if (upm != null)
    {
      upm.setCsort(this.paymode.getCsort());
      upm.setIntro(this.paymode.getIntro());
      upm.setName(this.paymode.getName());
      upm.setRate(this.paymode.getRate());
      upm.setFocrating(this.paymode.getFocrating());
      Attachment attachment = upload();
      Attachment oimage = upm.getImagee();
      if (attachment != null) {
        upm.setImagee(attachment);
        upm.setImage(attachment.getId());
      }
      this.payModeService.update(upm);
      
      if ((attachment != null) && (oimage != null)) {
        this.attachmentService.delete(oimage.getId());
      }
    }
    return "success";
  }
  
  public String updatePaySetting()
    throws Exception
  {
    this.paymode = this.payModeService.findById(this.pm);
    for (int i = 0; i < this.paysettings.size(); i++)
    {
      PaySetting setting = (PaySetting)this.paysettings.get(i);
      String id = existId(this.paymode, setting.getProperty());
      setting.setPaymode(this.paymode.getId());
      if (!id.equals("")) {
        setting.setId(id);
        this.paySettingService.update(setting);
      } else {
        setting.setId(getUuid());
        this.paySettingService.save(setting);
      }
    }
    return "success";
  }
  
  private String existId(PayMode paymode, String property)
    throws Exception
  {
    String id = "";
    PaySettingDC dc = new PaySettingDC();
    dc.setProperty(property);
    dc.setPaymode(paymode.getId());
    List list = this.paySettingService.findByPaySetting(dc);
    if (list.size() > 0)
    {
      PaySetting sti = (PaySetting)list.get(0);
      id = sti.getId();
    }
    return id;
  }
  
  public String getPm()
  {
    return this.pm;
  }
  
  public void setPm(String pm)
  {
    this.pm = pm;
  }
  
  public PayMode getPaymode()
  {
    return this.paymode;
  }
  
  public void setPaymode(PayMode paymode)
  {
    this.paymode = paymode;
  }
  
  public List getList()
  {
    return this.list;
  }
  
  public void setList(List list)
  {
    this.list = list;
  }
  
  public List<PaySetting> getPaySettings()
  {
    return this.paysettings;
  }
  
  public void setSettings(List<PaySetting> paysettings)
  {
    this.paysettings = paysettings;
  }
}
