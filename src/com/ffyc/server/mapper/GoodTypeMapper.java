package com.ffyc.server.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Brand;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.GoodType;
import com.ffyc.server.model.SpecItem;

public interface GoodTypeMapper {
	public void save(GoodType goodtype);

	public void update(GoodType goodtype);

	public void delete(@Param("id") String id);

	public GoodType findById(@Param("id") String id);

	public List<GoodType> findAll();
	
	public List<Brand> getBrands(@Param("id") String id); 
	
	public List<SpecItem> getSpecItems(@Param("id") String id);
	
	public List<ExtraAttr> getExtraAttrs(@Param("id") String id);
	
	public void insertBrands(@Param("id") String id,@Param("list") List<Brand> list);

	public void insertSpecItems(@Param("id") String id,@Param("list") List<SpecItem> list);

	public void insertExtraAttrs(@Param("id") String id,@Param("list") List<ExtraAttr> list);
	
	public void deleteBrands(@Param("id") String id);
	
	public void deleteSpecItems(@Param("id") String id);

	public void deleteExtraAttrs(@Param("id") String id);

}
