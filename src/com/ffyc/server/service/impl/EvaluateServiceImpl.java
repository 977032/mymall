package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.EvaluateMapper;
import com.ffyc.server.model.Evaluate;
import com.ffyc.server.service.EvaluateService;

@Service("evaluateService")
public class EvaluateServiceImpl extends BaseServiceImpl implements EvaluateService{

    @Autowired
    private EvaluateMapper evaluateMapper;
	
	@Override
	public void save(Evaluate evaluate) {
		evaluateMapper.save(evaluate);
	}
	
	@Override
	public void update(Evaluate evaluate) {
		evaluateMapper.update(evaluate);
	}

	@Override
	public void delete(String id) {
		evaluateMapper.delete(id);
	}

	@Override
	public Evaluate findById(String id) {
		return evaluateMapper.findById(id);
	}

	@Override
	public List<Evaluate> findAll() {
		return evaluateMapper.findAll();
	}
}
