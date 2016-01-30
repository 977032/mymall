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
import com.ffyc.server.mapper.SpecValueMapper;
import com.ffyc.server.model.Attachment;
import com.ffyc.server.model.Category;
import com.ffyc.server.model.SpecValue;
import com.ffyc.server.service.SpecValueService;
import com.ffyc.server.utils.PaginationSupport;

@Service("specValueService")
public class SpecValueServiceImpl extends BaseServiceImpl implements SpecValueService {

    @Autowired
    private SpecValueMapper specValueMapper;
    
    @Autowired
    private AttachmentMapper attachmentMapper;
	
	@Override
	public void save(SpecValue specValue) {
		specValueMapper.save(specValue);
	}
	
	@Override
	public void update(SpecValue specValue) {
		specValueMapper.update(specValue);
	}

	@Override
	public void delete(String id) {
		specValueMapper.delete(id);
	}

	@Override
	public SpecValue findById(String id) {
		return specValueMapper.findById(id);
	}

	@Override
	public List<SpecValue> findAll() {
		return specValueMapper.findAll();
	}

	@Override
	public List<SpecValue> findBySpecValue(SpecValue dc) {
		return specValueMapper.findBySpecValue(dc, null, null);
	}

	@Override
	public List<SpecValue> findBySpecValue(SpecValue dc, Integer startIndex,
			Integer pageSize) {
		return specValueMapper.findBySpecValue(dc, startIndex, pageSize);
	}

	@Override
	public int getCountBySpecValue(SpecValue dc) {
		return specValueMapper.getCountBySpecValue(dc);
	}

	@Override
	public PaginationSupport findPageBySpecValue(SpecValue dc, Integer page,
			Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.specValueMapper.getCountBySpecValue(dc);
		List list = this.specValueMapper.findBySpecValue(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}

	@Override
	public void getImagees(List<SpecValue> list) {
		if(list.size() == 0) return;
		Set<String> idSet = new HashSet<String>();
	    for(SpecValue specvalue: list){
	    	if(StringUtils.isNotEmpty(specvalue.getImage())){
	    		idSet.add(specvalue.getImage());
	    	}
	    }
	    if(idSet.size() ==0 ) return;
	    List<String> ids = new ArrayList<String>(idSet); 
	    List<Attachment> attachmentList = attachmentMapper.findByIds(ids);
	    HashMap<String,Attachment> map = new HashMap<String,Attachment>();
	    for(Attachment attachment: attachmentList){
	    	map.put(attachment.getId(), attachment);
	    }
	    for(SpecValue specvalue:list){
	    	if(map.containsKey(specvalue.getImage())){
	    		specvalue.setImagee(map.get(specvalue.getImage()));
	    	}
	    }
		
	}
	
	
	
	
}
