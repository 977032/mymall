package com.ffyc.server.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.ChannelContentMapper;
import com.ffyc.server.mapper.DocumentCategoryMapper;
import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Locale;
import com.ffyc.server.service.ChannelContentService;
import com.ffyc.server.utils.PaginationSupport;

@Service("channelContentService")
public class ChannelContentServiceImpl extends BaseServiceImpl implements ChannelContentService {

    @Autowired
    private ChannelContentMapper channelContentMapper;
    
    @Autowired
    private LocaleMapper localeMapper;
    
    @Autowired
    private DocumentCategoryMapper documentCategoryMapper;
	
	@Override
	public void save(ChannelContent channelContent) {
		channelContentMapper.save(channelContent);
	}
	
	@Override
	public void update(ChannelContent channelContent) {
		channelContentMapper.update(channelContent);
	}

	@Override
	public void delete(String id) {
		channelContentMapper.delete(id);
	}

	@Override
	public ChannelContent findById(String id) {
		return channelContentMapper.findById(id);
	}

	@Override
	public List<ChannelContent> findAll() {
		return channelContentMapper.findAll();
	}

	@Override
	public List<ChannelContent> findAllGlobal() {
		ChannelContent chcontent = new ChannelContent();
		chcontent.setGlobal("yes");
		List<ChannelContent> list = this.findByChannelContent(chcontent);
		return list;
	}

	@Override
	public List<ChannelContent> findAllGlobal(Locale locale) {
	    ChannelContent dc = new ChannelContent();
	    dc.setLocale(locale.getId());
	    dc.setGlobal("yes");
	    List<ChannelContent> list = this.findByChannelContent(dc);
		return list;
	}

	@Override
	public List<ChannelContent> findByChannelContent(ChannelContent chcontent) {
		return channelContentMapper.findByChannelContent(chcontent, null, null);
	}

	@Override
	public List<ChannelContent> findByChannelContent(ChannelContent chcontent,Integer startIndex,Integer pageSize) {
		return channelContentMapper.findByChannelContent(chcontent, startIndex, pageSize);
	}

	@Override
	public int getCountByChannelContent(ChannelContent chcontent) {
		return channelContentMapper.getCountByChannelContent(chcontent);
	}

	@Override
	public PaginationSupport findPageByChannelContent(ChannelContent chcontent,
			Integer page, Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex =  (page-1) * pageSize;
		int totalCount = this.channelContentMapper.getCountByChannelContent(chcontent);
		List<ChannelContent> list = this.channelContentMapper.findByChannelContent(chcontent, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list,totalCount,startIndex,pageSize);
		return ps;
	}

	@Override
	public void getLocales(List<ChannelContent> list) {
		if(list.size()==0) return;
		Set<String> idSet = new HashSet<String>();
	    for(ChannelContent chconstent: list){
	    	if(StringUtils.isNotEmpty(chconstent.getLocale())){
	    		idSet.add(chconstent.getLocale());
	    	}
	    }
	    if(idSet.size() ==0 ) return;
	    List<String> ids = new ArrayList<String>(idSet); 
	    List<Locale> localeList = localeMapper.findByIds(ids);
	    HashMap<String,Locale> map = new HashMap<String,Locale>();
	    for(Locale locale: localeList){
	    	map.put(locale.getId(), locale);
	    }
	    for(ChannelContent chcontent:list){
	    	if(map.containsKey(chcontent.getLocale())){
	    		chcontent.setLocalee(map.get(chcontent.getLocale()));
	    	}
	    }
	}
	
	public void getDocumentCategorys(List<ChannelContent> list){
		if(list == null || list.size()==0) return;
		Set<String> idSet = new HashSet<String>();
	    for(ChannelContent chconstent: list){
	    	idSet.add(chconstent.getDoccate());
	    }
	    List<String> ids = new ArrayList<String>(idSet); 
	    List<DocumentCategory> doccateList = documentCategoryMapper.findByIds(ids);
	    HashMap<String,DocumentCategory> map = new HashMap<String,DocumentCategory>();
	    for(DocumentCategory doccate: doccateList){
	    	map.put(doccate.getId(), doccate);
	    }
	    for(ChannelContent chcontent:list){
	    	if(map.containsKey(chcontent.getDoccate())){
	    		chcontent.setDoccatee(map.get(chcontent.getDoccate()));
	    	}
	    }
	}

}
