package com.ffyc.server.action.manager;

import java.util.Date;

import com.ffyc.server.action.manager.BaseAction;

public class BankAction 
extends BaseAction
{
 private static final long serialVersionUID = 8658665783932666545L;
 private Date min;
 private Date max;
 private Double total_balance;
 
 public String execute()
   throws Exception
 {
   this.total_balance = this.bankRunAccountService.getTotalBalance(this.min, this.max);
   return "success";
 }
 
 public Date getMin()
 {
   return this.min;
 }
 
 public void setMin(Date min)
 {
   this.min = min;
 }
 
 public Date getMax()
 {
   return this.max;
 }
 
 public void setMax(Date max)
 {
   this.max = max;
 }
 
 public Double getTotal_balance()
 {
   return this.total_balance;
 }
 
 public void setTotal_balance(Double totalBalance)
 {
   this.total_balance = totalBalance;
 }
}
