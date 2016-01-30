package com.ffyc.server.action.manager;

import java.io.PrintWriter;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.json.annotations.JSON;

import com.ffyc.server.model.District;

public class DistrictAjaxAction 
extends BaseAction
{
  private static final long serialVersionUID = -3241333116976701481L;
  private int id;
  private List list;
  
  public String execute()
    throws Exception
  {
    JsonConfig config = new JsonConfig();
    config.setJsonPropertyFilter(new PropertyFilter()
    {
      public boolean apply(Object source, String name, Object value)
      {
        if ((name.equals("district")) || (name.equals("districts")) || 
          (name.equals("areas")) || 
          (name.equals("address"))) {
          return true;
        }
        return false;
      }
    });
    if (this.id > 0)
    {
    	District district = this.districtService.findById(Integer.valueOf(this.id));
      if (district != null)
      {
        District dis = new District();
        dis.setPid(new Integer(this.id));
        this.list = this.districtService.findByDistrict(dis);
        //JSONArray localJSONArray = JSONArray.fromObject(this.list, config);
      }
    }
    else
    {
      this.list = this.districtService.findAllRoot();
      //JSONArray localJSONArray = JSONArray.fromObject(this.list, config);
    }
    
    return "success";
  }
  
  @JSON(serialize=false)
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public List getList()
  {
    return this.list;
  }
  
  public void setList(List list)
  {
    this.list = list;
  }
}
