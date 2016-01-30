package com.ffyc.server.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.dc.BankRunAccountDC;
import com.ffyc.server.mapper.BankRunAccountMapper;
import com.ffyc.server.model.BankRunAccount;
import com.ffyc.server.service.BankRunAccountService;
import com.ffyc.server.utils.PaginationSupport;

@Service("bankRunAccountService")
public class BankRunAccountServiceImpl extends BaseServiceImpl implements
		BankRunAccountService {

	@Autowired
	private BankRunAccountMapper bankRunAccountMapper;

	@Override
	public void save(BankRunAccount bankRunAccount) {
		bankRunAccountMapper.save(bankRunAccount);

	}

	@Override
	public void update(BankRunAccount bankRunAccount) {
		bankRunAccountMapper.update(bankRunAccount);

	}

	@Override
	public void delete(String id) {
		bankRunAccountMapper.delete(id);
	}

	@Override
	public BankRunAccount findById(String id) {
		return bankRunAccountMapper.findById(id);
	}

	@Override
	public List<BankRunAccount> findAll() {
		return bankRunAccountMapper.findAll();
	}
	
	@Override
	public Double getTotalBalance(Date min, Date max) {
		Double total = new Double(0.0D);
		String status = "ok";
		Double ret = this.bankRunAccountMapper
				.getTotalBalance(status, min, max);
		if (ret != null) {
			total = ret;
		}
		return total;
	}

	@Override
	public List<BankRunAccount> findByBankRunAccount(BankRunAccountDC dc) {
		return bankRunAccountMapper.findByBankRunAccount(dc, null, null);
	}

	@Override
	public List<BankRunAccount> findByBankRunAccount(BankRunAccountDC dc,
			Integer startIndex, Integer pageSize) {
		return bankRunAccountMapper.findByBankRunAccount(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByBankRunAccount(BankRunAccountDC dc) {
		return bankRunAccountMapper.getCountByBankRunAccount(dc);
	}

	@Override
	public PaginationSupport findPageByBankRunAccount(BankRunAccountDC dc,
			Integer page, Integer pageSize) {
		if(page <= 0) page = 1;
		int startIndex = (page - 1) * pageSize ;
		int totalCount = this.bankRunAccountMapper.getCountByBankRunAccount(dc);
		List list = this.bankRunAccountMapper.findByBankRunAccount(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
		return ps;
	}



}
