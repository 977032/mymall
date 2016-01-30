package com.ffyc.server.action.member;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.District;
import com.ffyc.server.model.Address;

public class AddressAction 
extends BaseAction
{
  private static final long serialVersionUID = -7357355245586878102L;
  private List list;
  private List districtroot;
  private String aid = "";
  private Address address;
  private String editDistrict;
  
  public String execute()
    throws Exception
  {
    this.districtroot = this.districtService.findAllRoot();
    Address dc = new Address();
    dc.setMember(getMemberFromSession().getId());
    
    this.list = this.addressService.findByAddress(dc);
    for(int i=0;i<list.size();i++){
    	Address add =  (Address)list.get(i);
    	if(add.getDistrictt()!=null){
    		add.getDistrictt().getDistrict();
    	}
    }
    if (StringUtils.isNotEmpty(this.aid))
    {
      this.address = this.addressService.findById(this.aid);
      
      District dt = this.address.getDistrictt().getDistrict();
      Iterator it = dt.getDistricts().iterator();
      while (it.hasNext())
      {
        District subdt = (District)it.next();
        if (subdt.getId().equals(this.address.getDistrictt().getId())) {
          this.editDistrict = (this.editDistrict + "<option value='" + subdt.getId() + "' selected='selected'>" + subdt.getName() + "</option>");
        } else {
          this.editDistrict = (this.editDistrict + "<option value='" + subdt.getId() + "'>" + subdt.getName() + "</option>");
        }
      }
    }
    return "success";
  }
  
  public String delete()
    throws Exception
  {
    if (StringUtils.isNotEmpty(this.aid))
    {
      Address address = this.addressService.findById(this.aid);
      if (address != null) {
        this.addressService.delete(address.getId());
      }
    }
    return "success";
  }
  
  public String setDefault()
    throws Exception
  {
    if (StringUtils.isNotEmpty(this.aid))
    {
      Address address = this.addressService.findById(this.aid);
      if (address != null)
      {
    	Address dc = new Address();
    	dc.setMember(getMemberFromSession().getId());
    	dc.setStatus("default");
        this.list = this.addressService.findByAddress(dc);
        for (int i = 0; i < this.list.size(); i++)
        {
          Address sd = (Address)this.list.get(i);
          sd.setStatus(Address.STATUS_NORMAL);
          this.addressService.update(sd);
        }
        address.setStatus("default");
        this.addressService.update(address);
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
  
  public List getDistrictroot()
  {
    return this.districtroot;
  }
  
  public void setDistrictroot(List districtroot)
  {
    this.districtroot = districtroot;
  }
  
  public String getAid()
  {
    return this.aid;
  }
  
  public void setAid(String aid)
  {
    this.aid = aid;
  }
  
  public Address getAddress()
  {
    return this.address;
  }
  
  public void setAddress(Address address)
  {
    this.address = address;
  }
 
  
  public String getEditDistrict()
  {
    return this.editDistrict;
  }
  
  public void setEditDistrict(String editDistrict)
  {
    this.editDistrict = editDistrict;
  }
}
