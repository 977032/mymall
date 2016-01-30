package com.ffyc.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import com.ffyc.server.mapper.PermissionMapper;
import com.ffyc.server.utils.BeanFactory;

public class Permission
  implements Serializable
{
  private static final long serialVersionUID = -6462895411885202075L;
  
  private String id;
  private String pid;
  private String mgroup;
  private String member;
  private String manager;
  private String group;
  
  private String name;
  private String code;
  private Integer read = new Integer(0);
  private Integer write = new Integer(0);
  private Integer delete = new Integer(0);
  
  private Permission permission;
  private List<Permission> permissions;

  
  public static final int READ = 4 ;
  public static final int WRITE = 2 ;
  public static final int DELETE = 1 ;
  
  public Permission() {}
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public Integer getRead()
  {
    return this.read;
  }
  
  public void setRead(Integer read)
  {
    if (read != null) {
      this.read = read;
    } else {
      this.read = Integer.valueOf(0);
    }
  }
  
  public Integer getWrite()
  {
    return this.write;
  }
  
  public void setWrite(Integer write)
  {
    if (write != null) {
      this.write = write;
    } else {
      this.write = Integer.valueOf(0);
    }
  }
  
  public Integer getDelete()
  {
    return this.delete;
  }
  
  public void setDelete(Integer delete)
  {
    if (delete != null) {
      this.delete = delete;
    } else {
      this.delete = Integer.valueOf(0);
    }
  }

public String getPid() {
	return pid;
}

public void setPid(String pid) {
	this.pid = pid;
}

public String getMgroup() {
	return mgroup;
}

public void setMgroup(String mgroup) {
	this.mgroup = mgroup;
}

public String getMember() {
	return member;
}

public void setMember(String member) {
	this.member = member;
}

public String getManager() {
	return manager;
}

public void setManager(String manager) {
	this.manager = manager;
}

public String getGroup() {
	return group;
}

public void setGroup(String group) {
	this.group = group;
}

public Permission getPermission() {
	if (StringUtils.isNotEmpty(this.pid) && this.permission ==null){
		PermissionMapper permissionMapper = BeanFactory.getInstance().getPermissionMapper();
		permission = permissionMapper.findById(pid);
	}
	return permission;
}

public List<Permission> getPermissions() {
	if (this.permissions ==null){
		Permission permission = new Permission();
		permission.setPid(id);
		PermissionMapper permissionMapper = BeanFactory.getInstance().getPermissionMapper();
		List list= permissionMapper.findByPermission(permission);
		if(list!=null){
			permissions = list;
		}else{
			permissions = new ArrayList<Permission>();
		}
	}
	return permissions;
}

public void setPermission(Permission permission) {
	this.permission = permission;
}

public void setPermissions(List<Permission> permissions) {
	this.permissions = permissions;
}

}
