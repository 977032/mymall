package com.ffyc.server.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.ExtraAttrValue;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Locale;

public interface GoodMapper {

	public void save(Good good);

	public void update(Good good);

	public void delete(@Param("id") String id);

	public Good findById(@Param("id") String id);

	public List<Good> findAll();

	public List<Good> findByGood(@Param("good") Good good,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	public int getCountByGood(@Param("good") Good good);

	public List<Good> findBySql(@Param("sql") String sql,
			@Param("orderby") String orderby,
			@Param("startIndex") Integer startIndex,
			@Param("pageSize") Integer pageSize);

	public int getCountBySql(@Param("sql") String sql);

	public List<Good> findByLocalAndCategory(@Param("locale") String locale,
			@Param("category") String category, @Param("status") String status,
			@Param("keywords") String[] keywords);
	
	public List<ExtraAttrValue> getExtraAttrValues(@Param("id") String id);
	
	public void insertExtraAttrValues(@Param("id") String id,@Param("list") List<ExtraAttrValue> list);
	
	public void deleteExtraAttrValues(@Param("id") String id);

}
