package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.model.Locale;
import com.ffyc.server.service.LocaleService;

@Service("localeService")
public class LocaleServiceImpl extends BaseServiceImpl implements LocaleService {


    @Autowired
    private LocaleMapper localeMapper;
	
	@Override
	public void save(Locale locale) {
		localeMapper.save(locale);
	}
	
	@Override
	public void update(Locale locale) {
		localeMapper.update(locale);
	}

	@Override
	public void delete(String id) {
		localeMapper.delete(id);
	}

	@Override
	public Locale findById(String id) {
		return localeMapper.findById(id);
	}

	@Override
	public List<Locale> findAll() {
		return localeMapper.findAll();
	}


}
