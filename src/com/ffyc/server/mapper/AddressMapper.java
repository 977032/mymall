package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Address;
import com.ffyc.server.model.Attachment;

public interface AddressMapper
{  
  public void save(Address address);
  
  public void update(Address address);
  
  public void delete(@Param("id") String id);
  
  public Address findById(@Param("id") String id);
  
  public List<Address> findAll();
  
  public List<Address> findByAddress(@Param("address") Address address,@Param("startIndex") Integer startIndex,@Param("pageSize") Integer pageSize);
  
  public int getCountByAddress(@Param("address") Address address);

}
