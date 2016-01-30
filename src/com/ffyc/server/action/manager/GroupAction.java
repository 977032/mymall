package com.ffyc.server.action.manager;

import java.util.List;

import com.ffyc.server.action.manager.BaseAction;

public class GroupAction
extends BaseAction
{
 private static final long serialVersionUID = 8597188110112659133L;
 private List list;
 private String gid;
 
 public String execute()
   throws Exception
 {
   this.list = this.groupService.findAll();
   return "success";
 }
 
 public String delete()
   throws Exception
 {
   this.groupService.delete(this.gid);
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
 
 public String getGid()
 {
   return this.gid;
 }
 
 public void setGid(String gid)
 {
   this.gid = gid;
 }
}

