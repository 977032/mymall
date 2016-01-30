package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.PayMode;

public class PayModeAction 
extends BaseAction
{
 private static final long serialVersionUID = 844686116734641074L;
 private List list;
 private String[] checkbox;
 
 public String execute()
   throws Exception
 {
   this.list = this.payModeService.findAll();
   return "success";
 }
 
 public String delete()
   throws Exception
 {
	 if ((this.checkbox != null) && (this.checkbox.length > 0)){
		 for (int i = 0; i < this.checkbox.length; i++)
		   {
		     PayMode paymode = this.payModeService.findById(this.checkbox[i]);
		     this.payModeService.delete(paymode.getId());
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
 
 public String[] getCheckbox()
 {
   return this.checkbox;
 }
 
 public void setCheckbox(String[] checkbox)
 {
   this.checkbox = checkbox;
 }
}
