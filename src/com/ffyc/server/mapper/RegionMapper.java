package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Region;

public interface RegionMapper {
	public void save(Region region);
	
	public void update(Region region);

	public void delete(@Param("id") String id);

	public Region findById(@Param("id") String id);

	public List<Region> findAll();
}
