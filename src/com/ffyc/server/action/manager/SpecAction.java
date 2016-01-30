package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.GoodType;
import com.ffyc.server.model.Spec;
import com.ffyc.server.model.SpecItem;

public class SpecAction 
extends BaseAction
{
 private static final long serialVersionUID = 2693491936123617800L;
 private String gid;
 private Good good;
 private List list;
 private List silist;
 private String[] checkbox;
 
 public String execute()
   throws Exception
 {
   this.good = this.goodService.findById(this.gid);
   Spec dc = new Spec();
   dc.setGood(this.good.getId());
   this.list = specService.findBySpec(dc);
   if (this.good.getGoodtypee() != null)
   {
	 this.silist = this.good.getGoodtypee().getSpecitems();
     return "success";
   }
   this.notice.setStatus(false);
   this.notice.setCode("goods_haven't_goodtype");
   this.notice.setTitle("提示：");
   this.notice.setBody("该商品没有设置商品类型，无法录入商品规格信息。");
   return "error";
 }
 
 public String delete()
   throws Exception
 {
   for (int i = 0; i < this.checkbox.length; i++)
   {
     Spec spec = this.specService.findById(this.checkbox[i]);
     this.specService.delete(spec.getId());
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
 
 public Good getGood()
 {
   return this.good;
 }
 
 public void setGood(Good good)
 {
   this.good = good;
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
 
 public String[] getCheckbox()
 {
   return this.checkbox;
 }
 
 public void setCheckbox(String[] checkbox)
 {
   this.checkbox = checkbox;
 }
}
