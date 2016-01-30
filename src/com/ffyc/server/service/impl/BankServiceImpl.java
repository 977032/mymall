package com.ffyc.server.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.BankMapper;
import com.ffyc.server.model.Bank;
import com.ffyc.server.service.BankService;

@Service("bankService")
public class BankServiceImpl extends BaseServiceImpl implements BankService {

    @Autowired
    private BankMapper bankMapper;
	

	@Override
	public void save(Bank bank) {
		bankMapper.save(bank);
	}
	
	@Override
	public void update(Bank bank) {
		bankMapper.update(bank);
	}

	@Override
	public void delete(String id) {
		bankMapper.delete(id);
	}

	@Override
	public Bank findById(String id) {
		return bankMapper.findById(id);
	}

	@Override
	public List<Bank> findAll() {
		return bankMapper.findAll();
	}

	@Override
	public Bank openAccount() {
		Bank bank = new Bank();
	    bank.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	    String randint = RandomStringUtils.randomNumeric(8);
	    bank.setAccount("88888888" + randint);
	    bank.setStatus(Bank.STATUS_NORMAL);
	    bank.setBalance(new Double(0.0D));
	    bank.setRemarks("资金账户开户。");
	    bank.setCtime(new Timestamp(System.currentTimeMillis()));
	    this.bankMapper.save(bank);
	    return bank;
	}

}
