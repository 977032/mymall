package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.UserBasicInfo;
import com.ffyc.server.vo.LoginVO;

public interface ManagerMapper
{
    public Manager findById(@Param("id") String id);
    
    public Manager findByAccount(@Param("account") String account);

    public void save(Manager manager);

    public void update(Manager manager);
    
    public List<Manager> findAll();
    	
    public List<UserBasicInfo> searchManagerByName(@Param("name") String name,@Param("start") int start, @Param("count") int count);

	public int searchManagerByNameCount(@Param("name") String name);
	
    public int isExists(@Param("account") String account, @Param("password") String password);
    
    public void updatePassword(@Param("id") String id, @Param("password") String password);
    
    public List<Manager> findByManager(@Param("manager") Manager manager,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
    
    public int getCountByManager(@Param("manager") Manager manager);


}
