package com.ffyc.server.action.publicer;

import java.util.Iterator;
import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Spec;
import com.ffyc.server.model.SpecValue;

public class SpecAction 
extends BaseAction
{
  private static final long serialVersionUID = -4922966083317215466L;
  private String gid;
  private String svid;
  
  public String execute()
    throws Exception
  {
    Good good = this.goodService.findById(this.gid);
    SpecValue specvalue = this.specValueService.findById(this.svid);
    Iterator it = good.getSpecs().iterator();
    Iterator it2;
    while (it.hasNext())
    {
      Spec spec = (Spec)it.next();
      System.out.println("此规格：" + spec.getPrice());
      it2 = spec.getSpecvalues().iterator();
      while(it2.hasNext()){
    	  SpecValue sv = (SpecValue)it2.next();
          System.out.println("规格值：" + sv.getName());
          if (sv.equals(specvalue)) {
            System.out.println("找到相同的规格值：" + specvalue.getName());
          }
      }
    }
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
  
  public String getSvid()
  {
    return this.svid;
  }
  
  public void setSvid(String svid)
  {
    this.svid = svid;
  }
}
