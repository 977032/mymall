package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.Good;
import com.ffyc.server.model.Spec;
import com.ffyc.server.model.SpecItem;
import com.ffyc.server.model.SpecValue;

public interface SpecMapper {
	public void save(Spec spec);

	public void update(Spec spec);

	public void delete(@Param("id") String id);

	public Spec findById(@Param("id") String id);

	public List<Spec> findAll();

	public List<SpecValue> getSpecValues(@Param("id") String id);

	public List<Spec> findBySpec(@Param("spec") Spec spec,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	public int getCountBySpec(@Param("spec") Spec spec);
	
	public void insertSpecValues(@Param("id") String id,@Param("list") List<SpecValue> list);
	
	public void deleteSpecValues(@Param("id") String id);
}
