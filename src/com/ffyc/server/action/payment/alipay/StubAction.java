package com.ffyc.server.action.payment.alipay;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.utils.Notice;

public class StubAction extends BaseAction {
	private static final long serialVersionUID = 2172102901901762913L;
	private Notice notice = new Notice();
	private String body;
	private String logistics_type;
	private String logistics_fee;
	private String subject;
	private String receive_address;
	private String receive_phone;
	private String receive_name;
	private String notify_url;
	private Integer discount;
	private String return_url;
	private String _input_charset;
	private Double price;
	private String service;
	private String receive_mobile;
	private Integer quantity;
	private String partner;
	private String receive_zip;
	private String seller_email;
	private String logistics_payment;
	private String payment_type;
	private String show_url;
	private String sign;
	private String sign_type;

	public String execute() throws Exception {
		return "success";
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getLogistics_type() {
		return logistics_type;
	}

	public void setLogistics_type(String logistics_type) {
		this.logistics_type = logistics_type;
	}

	public String getLogistics_fee() {
		return logistics_fee;
	}

	public void setLogistics_fee(String logistics_fee) {
		this.logistics_fee = logistics_fee;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getReceive_address() {
		return receive_address;
	}

	public void setReceive_address(String receive_address) {
		this.receive_address = receive_address;
	}

	public String getReceive_phone() {
		return receive_phone;
	}

	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}

	public String getReceive_name() {
		return receive_name;
	}

	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getReturn_url() {
		return return_url;
	}

	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getReceive_mobile() {
		return receive_mobile;
	}

	public void setReceive_mobile(String receive_mobile) {
		this.receive_mobile = receive_mobile;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getReceive_zip() {
		return receive_zip;
	}

	public void setReceive_zip(String receive_zip) {
		this.receive_zip = receive_zip;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getLogistics_payment() {
		return logistics_payment;
	}

	public void setLogistics_payment(String logistics_payment) {
		this.logistics_payment = logistics_payment;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getShow_url() {
		return show_url;
	}

	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	
}
