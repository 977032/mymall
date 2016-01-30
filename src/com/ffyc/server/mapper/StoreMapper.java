package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Region;
import com.ffyc.server.model.Store;

public interface StoreMapper {
	public void save(Store store);
	
	public void update(@Param("store") Store store);

	public void delete(@Param("id") String id);

	public Store findById(@Param("id") String id);

	public List<Store> findAll();
}
