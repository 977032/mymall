package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.District;
import com.ffyc.server.model.Logistic;
import com.ffyc.server.model.Area;

public class AreaCreateAction 
extends BaseAction
{
  private static final long serialVersionUID = 4323991936932121939L;
  private String ls;
  private Logistic logistic;
  private Area area = new Area();
  private int[] district;
  
  public String execute()
    throws Exception
  {
    this.logistic = this.logisticService.findById(this.ls);
    if (this.logistic != null)
    {
      this.area.setId(getUuid());
      this.area.setLogistic(logistic.getId());
      this.area.setLogisticc(this.logistic);
      if (this.district != null) {
        for (int i = 0; i < this.district.length; i++)
        {
          District dis = this.districtService.findById(Integer.valueOf(this.district[i]));
          this.area.getDistricts().add(dis);
        }
      }
      this.areaService.save(this.area);
      if(this.area.getDistricts().size()>0){
    	  this.area.insertDistrics();
      }
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
    this.logistic = logistic;
  }
  
  public Area getArea()
  {
    return this.area;
  }
  
  public void setrea(Area area)
  {
    this.area = area;
  }
  
  public int[] getDistrict()
  {
    return this.district;
  }
  
  public void setDistrict(int[] district)
  {
    this.district = district;
  }
}
