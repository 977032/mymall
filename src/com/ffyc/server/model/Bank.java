package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Bank
  implements Serializable
{
  private String id;
  private String account;
  private String status;
  private Double balance;
  private String remarks;
  private Timestamp ctime;
  
  public final static String STATUS_NORMAL = "normal";
  
  public Bank() {}
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getAccount()
  {
    return this.account;
  }
  
  public void setAccount(String account)
  {
    this.account = account;
  }
  
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
  
  public Double getBalance()
  {
    return this.balance;
  }
  
  public void setBalance(Double balance)
  {
    this.balance = balance;
  }
  
  public String getRemarks()
  {
    return this.remarks;
  }
  
  public void setRemarks(String remarks)
  {
    this.remarks = remarks;
  }
  
  public Timestamp getCtime()
  {
    return this.ctime;
  }
  
  public void setCtime(Timestamp ctime)
  {
    this.ctime = ctime;
  }
  
}

