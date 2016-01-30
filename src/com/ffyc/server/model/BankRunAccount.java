package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.BankMapper;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.PayModeMapper;
import com.ffyc.server.model.Bank;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.utils.BeanFactory;

public class BankRunAccount implements Serializable {

	private String id;
	private String bank;
	private Bank bankk;
	private String member;
	private Member memberr;
	private String manager;
	private Manager managerr;
	private String paymode;
	private PayMode paymodee;
	private String status;
	private Double balance;
	private Double getout;
	private Double payin;
	private String tabloid;
	private String remarks;
	private String mremarks;
	private Timestamp ctime;

	public BankRunAccount() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Bank getBankk() {
		if(StringUtils.isNotEmpty(this.bank) && this.bankk ==null){
			BankMapper bankMapper = BeanFactory.getInstance().getBankMapper();
			this.bankk = bankMapper.findById(this.bank);
		}
		return bankk;
	}

	public void setBankk(Bank bankk) {
		this.bankk = bankk;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public Member getMemberr() {
		if(StringUtils.isNotEmpty(this.member) && this.memberr ==null){
			MemberMapper memberMapper = BeanFactory.getInstance().getMemberMapper();
			this.memberr = memberMapper.findById(this.member);
		}
		return memberr;
	}

	public void setMemberr(Member memberr) {
		this.memberr = memberr;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Manager getManagerr() {
		if(StringUtils.isNotEmpty(this.manager) && this.managerr == null){
			ManagerMapper managerMapper = BeanFactory.getInstance().getManagerMapper();
			this.managerr = managerMapper.findById(this.manager);
		}
		return managerr;
	}

	public void setManagerr(Manager managerr) {
		this.managerr = managerr;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public PayMode getPaymodee() {
		if(StringUtils.isNotEmpty(this.paymode) && this.paymodee == null){
			PayModeMapper payModeMapper = BeanFactory.getInstance().getPayModeMapper();
			this.paymodee = payModeMapper.findById(this.paymode);
		}
		return paymodee;
	}

	public void setPaymodee(PayMode paymodee) {
		this.paymodee = paymodee;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getGetout() {
		return this.getout;
	}

	public void setGetout(Double getout) {
		this.getout = getout;
	}

	public Double getPayin() {
		return this.payin;
	}

	public void setPayin(Double payin) {
		this.payin = payin;
	}

	public String getTabloid() {
		return this.tabloid;
	}

	public void setTabloid(String tabloid) {
		this.tabloid = tabloid;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMremarks() {
		return this.mremarks;
	}

	public void setMremarks(String mremarks) {
		this.mremarks = mremarks;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}
}
