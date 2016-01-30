package com.ffyc.server.service;

import java.util.List;
import java.util.Set;

import com.ffyc.server.model.Brand;
import com.ffyc.server.model.ExtraAttr;
import com.ffyc.server.model.GoodType;
import com.ffyc.server.model.SpecItem;

public interface GoodTypeService extends BaseService {

	public void save(GoodType goodtype);

	public void update(GoodType goodtype);

	public void delete(String id);

	public GoodType findById(String id);

	public List<GoodType> findAll();

}