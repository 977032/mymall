package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.BankRunAccount;

public class BankRunAccountAction 
extends BaseAction
{
  private static final long serialVersionUID = -3708014681435174042L;
  private String bra;
  private BankRunAccount brunacc;
  
  public String execute()
    throws Exception
  {
    this.brunacc = this.bankRunAccountService.findById(this.bra);
    return "success";
  }
  
  public String delete()
    throws Exception
  {
	BankRunAccount brunacc = this.bankRunAccountService.findById(this.bra);
    if (brunacc != null) {
      this.bankRunAccountService.delete(brunacc.getId());
    }
    return "success";
  }
  
  public String getBra()
  {
    return this.bra;
  }
  
  public void setBra(String bra)
  {
    this.bra = bra;
  }
  
  public BankRunAccount getBrunacc()
  {
    return this.brunacc;
  }
  
  public void setBrunacc(BankRunAccount brunacc)
  {
    this.brunacc = brunacc;
  }
}

