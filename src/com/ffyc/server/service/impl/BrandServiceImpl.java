package com.ffyc.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.BrandMapper;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Brand;
import com.ffyc.server.service.BrandService;
import com.ffyc.server.utils.PaginationSupport;

@Service("brandService")
public class BrandServiceImpl extends BaseServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private AttachmentMapper attachmentMapper;
	
	@Override
	public void save(Brand brand) {
		brandMapper.save(brand);
	}
	
	@Override
	public void update(Brand brand) {
		brandMapper.update(brand);
	}


	@Override
	public void delete(String id) {
		brandMapper.delete(id);
	}

	@Override
	public Brand findById(String id) {
		return brandMapper.findById(id);
	}

	@Override
	public List<Brand> findAll() {
		return brandMapper.findAll();
	}

	@Override
	public List<Brand> findByBrand(Brand brand) {
		return brandMapper.findByBrand(brand, null, null);
	}

	@Override
	public List<Brand> findByBrand(Brand brand, Integer startIndex,Integer pageSize) {
		return brandMapper.findByBrand(brand, startIndex, pageSize);
	}

	@Override
	public int getCountByBrand(Brand brand) {
		return brandMapper.getCountByBrand(brand);
	}

	@Override
	public PaginationSupport findPageByBrand(Brand brand, Integer page,
			Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.brandMapper.getCountByBrand(brand);
		List list = this.brandMapper.findByBrand(brand, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}

	@Override
	public void getLogoos(List<Brand> list) {
		if(list.size()==0) return;
		Set<String> idSet = new HashSet<String>();
	    for(Brand brand: list){
	    	if(StringUtils.isNotEmpty(brand.getLogo())){
	    		idSet.add(brand.getLogo());
	    	}
	    }
	    if(idSet.size()==0) return;
	    List<String> ids = new ArrayList<String>(idSet); 
	    List<Attachment> attachmentList = attachmentMapper.findByIds(ids);
	    HashMap<String,Attachment> map = new HashMap<String,Attachment>();
	    for(Attachment attachment: attachmentList){
	    	map.put(attachment.getId(), attachment);
	    }
	    for(Brand brand:list){
	    	if(map.containsKey(brand.getLogo())){
	    		brand.setLogoo(map.get(brand.getLogo()));
	    	}
	    }
		
	}
	

}
