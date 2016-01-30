package com.ffyc.server.action.manager;

import java.util.Date;

import com.ffyc.server.dc.BankRunAccountDC;
import com.ffyc.server.model.BankRunAccount;
import com.ffyc.server.utils.PaginationSupport;

public class PayinGetoutAction extends BaseAction {
	private static final long serialVersionUID = 3065662145730395148L;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private Date min;
	private Date max;
	private String bra;
	private BankRunAccount brunacc = new BankRunAccount();

	public String execute() throws Exception {
		BankRunAccountDC dc = new BankRunAccountDC();
		if ((this.min != null) && (this.max != null)) {
			dc.setMax(this.max);
			dc.setMin(this.min);
		}
		this.ps = this.bankRunAccountService.findPageByBankRunAccount(dc,
				this.page, this.pagesize);
		return "success";
	}

	public String view() throws Exception {
		this.brunacc = this.bankRunAccountService.findById(this.bra);
		return "success";
	}

	public String audit() throws Exception {
		BankRunAccount ubrun = this.bankRunAccountService.findById(this.brunacc
				.getId());
		ubrun.setMremarks(this.brunacc.getMremarks());
		ubrun.setStatus(this.brunacc.getStatus());
		ubrun.setManager(getManagerFromSession().getId());
		this.bankRunAccountService.update(ubrun);
		return "success";
	}

	public PaginationSupport getPs() {
		return this.ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public int getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Date getMin() {
		return this.min;
	}

	public void setMin(Date min) {
		this.min = min;
	}

	public Date getMax() {
		return this.max;
	}

	public void setMax(Date max) {
		this.max = max;
	}

	public String getBra() {
		return this.bra;
	}

	public void setBra(String bra) {
		this.bra = bra;
	}

	public BankRunAccount getBrunacc() {
		return brunacc;
	}

	public void setBrunacc(BankRunAccount brunacc) {
		this.brunacc = brunacc;
	}

}
