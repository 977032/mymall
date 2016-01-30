package com.ffyc.server.action.member;

import java.util.List;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.dc.BankRunAccountDC;
import com.ffyc.server.model.BankRunAccount;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.PaginationSupport;
import com.ffyc.server.utils.alipay.PaymentUrl;

public class BankPayinAction extends BaseAction {
	private static final long serialVersionUID = 1285390137487002958L;
	private Notice notice = new Notice();
	private Member member;
	private List paymode_list;
	private Double payin = new Double(0.0D);
	private String remarks;
	private String pm = "";
	private String bra = "";
	private String urlparam;
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;

	public String execute() throws Exception {
		this.member = this.memberService.findById(getMemberFromSession()
				.getId());
		this.paymode_list = this.payModeService.findAll();

		this.member = this.memberService.findById(getMemberFromSession()
				.getId());
		BankRunAccountDC dc = new BankRunAccountDC();
		dc.setBank(this.member.getBank());
		this.ps = this.bankRunAccountService.findPageByBankRunAccount(dc,
				this.page, this.pagesize);
		return "success";
	}

	public String payin() throws Exception {
		PayMode paymode = this.payModeService.findById(this.pm);
		this.member = this.memberService.findById(getMemberFromSession()
				.getId());
		BankRunAccount brunacc = new BankRunAccount();
		brunacc.setPaymode(paymode.getId());
		brunacc.setId(getUuid());
		brunacc.setPayin(this.payin);
		brunacc.setRemarks(this.remarks);
		brunacc.setStatus("waitpay");

		Double ob = this.member.getBankk().getBalance();
		Double balance = Double.valueOf(this.payin.doubleValue()
				+ ob.doubleValue());

		brunacc.setBalance(balance);
		brunacc.setTabloid("充值");
		brunacc.setCtime(getTimestamp());
		brunacc.setBank(this.member.getBank());
		brunacc.setMember(this.member.getId());
		this.bankRunAccountService.update(brunacc);
		setBra(brunacc.getId());

		return "success";
	}

	public String alipay() throws Exception {
		BankRunAccount brunacc = this.bankRunAccountService.findById(this.bra);
		PayMode paymode = brunacc.getPaymodee();
		if ((paymode != null) && (paymode.getPmtype().equals(PayMode.PMTYPE_ALIPAY))) {
			Order order = new Order();
			order.setPaymodee(paymode);

			order.setCode(brunacc.getId());
			order.setSubject("充值");
			order.setActamount(brunacc.getPayin());
			order.setLogicost(new Double(0.0D));
			order.setBody(brunacc.getRemarks());

			PaymentUrl payment = new PaymentUrl();
			String preUrl = "http://" + this.request.getHeader("host")
					+ this.request.getContextPath();
			this.urlparam = payment.getUrlparam(order, preUrl);
			if (!this.urlparam.equals("")) {
				return "success";
			}
		}
		return "error";
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Double getPayin() {
		return this.payin;
	}

	public void setPayin(Double payin) {
		this.payin = payin;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPm() {
		return this.pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public String getBra() {
		return this.bra;
	}

	public void setBra(String bra) {
		this.bra = bra;
	}

	public String getUrlparam() {
		return this.urlparam;
	}

	public void setUrlparam(String urlparam) {
		this.urlparam = urlparam;
	}

	public List getPaymode_list() {
		return this.paymode_list;
	}

	public void setPaymode_list(List paymodeList) {
		this.paymode_list = paymodeList;
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
}
