package com.ffyc.server.service;

import com.ffyc.server.model.Address;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.utils.PaginationSupport;

import java.util.List;

public interface AddressService
  extends BaseService
{
  
  public void save(Address address);
  
  public void update(Address address);
  
  public void delete(String id);
  
  public Address findById(String id);
  
  public List<Address> findAll();
  
  public List<Address> findByAddress(Address dc);
  
  public List<Address> findByAddress(Address dc,Integer startIndex,Integer pageSize);
  
  public int getCountByAddress(Address dc);
  
  public PaginationSupport findPageByAddress(Address dc,Integer page, Integer pageSize) ;

}
