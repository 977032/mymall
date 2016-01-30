package com.ffyc.server.action.member;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.utils.alipay.PaymentUrl;

public class PaymentAction extends BaseAction {
	private static final long serialVersionUID = -4606922977161231227L;
	private String oid;
	private Order order;
	private String urlparam;

	public String execute() throws Exception {
		this.order = this.orderService.findById(this.oid);
		this.order.getOrderitems();
		PayMode paymode = this.order.getPaymodee();
		if ((paymode != null) && (paymode.getPmtype().equals(PayMode.PMTYPE_ALIPAY))) {
			PaymentUrl payment = new PaymentUrl();
			String preUrl = "http://" + this.request.getHeader("host")
					+ this.request.getContextPath();
			this.urlparam = payment.getUrlparam(this.order, preUrl);
			//modify 
			this.urlparam = this.urlparam.replace("https://www.alipay.com/cooperate/gateway.do", "http://localhost/beautyfamily/alipay_gateway_stub.action");
			session.put("out_trade_no", order.getCode());
			//
			if (!this.urlparam.equals("")) {
				return "success";
			}
		} else {
			if ((paymode != null) && (paymode.getPmtype().equals(PayMode.PMTYPE_OTHER))) {
				//modify offline
				return "details";
			}
		}
		this.notice.setStatus(false);
		this.notice.setTitle("提示：");
		this.notice.setCode("not implement paymode");
		this.notice.setBody("该支付方式暂不支持。");

		return "notice";
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getUrlparam() {
		return this.urlparam;
	}

	public void setUrlparam(String urlparam) {
		this.urlparam = urlparam;
	}
}
