package com.ffyc.server.action.manager;

import com.ffyc.server.action.manager.BaseAction;
import com.ffyc.server.model.Group;

public class GroupCreateAction 
extends BaseAction
{
  private static final long serialVersionUID = -7164511111530801035L;
  private Group group = new Group();
  
  public String execute()
    throws Exception
  {
    return "success";
  }
  
  public String create()
    throws Exception
  {
    this.group.setId(getUuid());
    if(this.group.getIsdefault()==null ||  this.group.getIsdefault().length()==0){
    	this.group.setIsdefault(Group.ISDEFAULT_NO);
    }
    this.group.setRequirepoints(new Integer(0));
    this.groupService.save(this.group);
    return "success";
  }
  
  public Group getGroup()
  {
    return this.group;
  }
  
  public void setGroup(Group group)
  {
    this.group = group;
  }
}
