package com.ffyc.server.action.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Brand;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.GoodType;
import com.ffyc.server.model.SpecItem;

public class GoodTypeUpdateAction 
extends BaseAction
{
  private static final long serialVersionUID = -603883545066651910L;
  private List list;
  private List silist;
  private List ealist;
  private String gt;
  private GoodType goodtype;
  private String[] checkbox;
  private String[] sicheckbox;
  private String[] eacheckbox;
  
  public String execute()
    throws Exception
  {
    this.goodtype = this.goodTypeService.findById(this.gt); 
    this.goodtype.getBrands();
    this.goodtype.getSpecitems();
    this.goodtype.getExtraattrs();
    this.list = this.brandService.findAll();
    this.silist = this.specItemService.findAll();
    this.ealist = this.extraAttrService.findAll();
    return "success";
  }
  
  public String update()
    throws Exception
  {
    GoodType ugt = this.goodTypeService.findById(this.goodtype.getId());
    ugt.setName(this.goodtype.getName());
    ugt.setAlias(this.goodtype.getAlias());
    ugt.setBrands(new ArrayList<Brand>());
    ugt.setSpecitems(new ArrayList<SpecItem>());
    ugt.setExtraattrs(new ArrayList<ExtraAttr>());
    if ((this.checkbox != null) && (this.checkbox.length > 0)) {
      for (int i = 0; i < this.checkbox.length; i++)
      {
        Brand brand = this.brandService.findById(this.checkbox[i]);
        ugt.getBrands().add(brand);
      }
    }
    if ((this.sicheckbox != null) && (this.sicheckbox.length > 0)) {
      for (int i = 0; i < this.sicheckbox.length; i++)
      {
        SpecItem specitem = this.specItemService.findById(this.sicheckbox[i]);
        ugt.getSpecitems().add(specitem);
      }
    }
    if ((this.eacheckbox != null) && (this.eacheckbox.length > 0)) {
		for (int i = 0; i < this.eacheckbox.length; i++) {
			ExtraAttr extraattr = this.extraAttrService
					.findById(this.eacheckbox[i]);
			ugt.getExtraattrs().add(extraattr);
		}
	}

    ugt.deleteBrands();
    ugt.deleteSpecItems();
    ugt.deleteExtraAttrs();
    if(ugt.getBrands().size()>0){
    	ugt.insertBrands();
    }
    if(ugt.getSpecitems().size()>0){
    	ugt.insertSpecItems();
    }
    if(ugt.getExtraattrs().size()>0){
    	ugt.insertExtraAttrs();
    }
    this.goodTypeService.update(ugt);
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
  
  public List getSilist()
  {
    return this.silist;
  }
  
  public void setSilist(List silist)
  {
    this.silist = silist;
  }
  
  public List getEalist()
  {
    return this.ealist;
  }
  
  public void setEalist(List ealist)
  {
    this.ealist = ealist;
  }
  
  public String getGt()
  {
    return this.gt;
  }
  
  public void setGt(String gt)
  {
    this.gt = gt;
  }
  
  public GoodType getGoodtype()
  {
    return this.goodtype;
  }
  
  public void setGoodtype(GoodType goodtype)
  {
    this.goodtype = goodtype;
  }
  
  public String[] getCheckbox()
  {
    return this.checkbox;
  }
  
  public void setCheckbox(String[] checkbox)
  {
    this.checkbox = checkbox;
  }
  
  public String[] getSicheckbox()
  {
    return this.sicheckbox;
  }
  
  public void setSicheckbox(String[] sicheckbox)
  {
    this.sicheckbox = sicheckbox;
  }
  
  public String[] getEacheckbox()
  {
    return this.eacheckbox;
  }
  
  public void setEacheckbox(String[] eacheckbox)
  {
    this.eacheckbox = eacheckbox;
  }
}
