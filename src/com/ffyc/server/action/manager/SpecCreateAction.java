package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Spec;
import com.ffyc.server.model.SpecValue;

public class SpecCreateAction 
extends BaseAction
{
  private static final long serialVersionUID = -8324470651719718547L;
  private String gid;
  private Good good;
  private Spec spec = new Spec();
  private List<SpecValue> specvalues;
  
  public String execute()
    throws Exception
  {
    this.spec.setId(getUuid());
    this.good = this.goodService.findById(this.gid);
    this.spec.setGoodd(this.good);
    this.spec.setGood(this.good.getId());
    if(this.specvalues!=null){
    	for (int i = 0; i < this.specvalues.size(); i++)
        {
          SpecValue sc = (SpecValue)this.specvalues.get(i);
          if (sc != null) {
            this.spec.getSpecvalues().add(sc);
          }
        }
    }
    this.specService.save(this.spec);
    this.spec.deleteSpecValues();
    this.spec.insertSpecValues();
    return "success";
  }
  
  public String getGid()
  {
    return this.gid;
  }
  
  public void setGid(String gid)
  {
    this.gid = gid;
  }
  
  public Good getGood()
  {
    return this.good;
  }
  
  public void setGood(Good good)
  {
    this.good = good;
  }
  
  public Spec getSpec()
  {
    return this.spec;
  }
  
  public void setSpec(Spec spec)
  {
    this.spec = spec;
  }
  
  public List<SpecValue> getSpecvalues()
  {
    return this.specvalues;
  }
  
  public void setSpecvalues(List<SpecValue> specvalues)
  {
    this.specvalues = specvalues;
  }
}
