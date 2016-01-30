package com.ffyc.server.action.payment.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.dc.OrderDC;
import com.ffyc.server.model.Bank;
import com.ffyc.server.model.BankRunAccount;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.model.PaySetting;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.alipay.CheckURL;
import com.ffyc.server.utils.alipay.SignatureHelper_return;

public class ReturnAction extends BaseAction {
	private static final long serialVersionUID = 685687646492891157L;
	private Notice notice = new Notice();
	
	public String execute() throws Exception {
		String out_trade_no = (String)session.get("out_trade_no");
		Order order = null;
		OrderDC dc = new OrderDC();
		dc.setCode(out_trade_no);
		List<Order> orders = this.orderService.findByOrder(dc);
		if (orders != null && orders.size() > 0) {
			order = orders.get(0);
		}
		if (order != null) {
			PayMode paymode = order.getPaymodee();
			if ((paymode != null) && (paymode.getPmtype().equals("alipay"))) {
				order.setStatus(Order.STATUS_WAIT_SELLER_SEND_GOODS);
				this.orderService.update(order);
				this.notice.setStatus(true);
				this.notice.setCode("ok");
				this.notice.setTitle("提示：");
				this.notice.setBody("充值成功。");
			}
		} else {
			BankRunAccount brunacc = this.bankRunAccountService
					.findById(out_trade_no);
			if (brunacc != null) {
				PayMode paymode = brunacc.getPaymodee();
				if ((paymode != null) && (paymode.getPmtype().equals("alipay"))) {
					brunacc.setStatus("ok");
					this.orderService.update(order);

					Bank bank = brunacc.getBankk();
					Double balance = Double.valueOf(bank.getBalance()
							.doubleValue()
							+ brunacc.getPayin().doubleValue());
					bank.setBalance(balance);
					this.bankService.update(bank);

					this.bankRunAccountService.update(brunacc);
					this.notice.setStatus(true);
					this.notice.setCode("ok");
					this.notice.setTitle("提示：");
					this.notice.setBody("充值成功。");
				}
			}
		}
		return "success";
	}

	public String execute_bk() throws Exception {
		String out_trade_no = this.request.getParameter("out_trade_no");		
		Order order = null;
		OrderDC dc = new OrderDC();
		dc.setCode(out_trade_no);
		List<Order> orders = this.orderService.findByOrder(dc);
		if (orders != null && orders.size() > 0) {
			order = orders.get(0);
		}
		if (order != null) {
			PayMode paymode = order.getPaymodee();
			if ((paymode != null) && (paymode.getPmtype().equals("alipay"))) {
				String partner = "";
				String key = "";

				List<PaySetting> set = paymode.getPaysettings();
				Iterator it = set.iterator();
				while (it.hasNext()) {
					PaySetting setting = (PaySetting) it.next();
					if (setting.getProperty().equals("partner")) {
						partner = setting.getValue();
					}
					if (setting.getProperty().equals("key")) {
						key = setting.getValue();
					}
				}
				String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?partner="
						+

						partner
						+ "&notify_id="
						+ this.request.getParameter("notify_id");
				
				String sign = this.request.getParameter("sign");

				String responseTxt = CheckURL.check(alipayNotifyURL);
				System.out.println("responseTxt: " + responseTxt);

				Map params = new HashMap();

				Map requestParams = this.request.getParameterMap();
				for (Iterator iter = requestParams.keySet().iterator(); iter
						.hasNext();) {
					String name = (String) iter.next();
					String[] values = (String[]) requestParams.get(name);
					String valueStr = "";
					for (int i = 0; i < values.length; i++) {
						valueStr = valueStr + values[i] + ",";
					}
					System.out.println(name + " : " + valueStr);
					params.put(name, valueStr);
				}
				String mysign = SignatureHelper_return.sign(params, key);

				if ((mysign.equals(this.request.getParameter("sign")))
						&& (responseTxt.equals("true"))) {

					order.setStatus("WAIT_SELLER_SEND_GOODS");
					this.orderService.update(order);

					this.notice.setStatus(true);
					this.notice.setCode(this.request
							.getParameter("trade_status"));
					this.notice.setTitle("提示：");
					this.notice.setBody("支付成功。");
				} else {
					System.out.println("fail");

					this.notice.setStatus(false);
					this.notice.setCode(this.request
							.getParameter("trade_status"));
					this.notice.setTitle("错误：");
					this.notice.setBody("支付失败。");
				}
			} else {
				this.notice.setStatus(false);
				this.notice.setCode(this.request.getParameter("trade_status"));
				this.notice.setTitle("错误：");
				this.notice.setBody("非支付宝支付的订单。");
			}
		} else {
			BankRunAccount brunacc = this.bankRunAccountService
					.findById(out_trade_no);
			if (brunacc != null) {
				PayMode paymode = brunacc.getPaymodee();
				if ((paymode != null) && (paymode.getPmtype().equals("alipay"))) {
					String partner = "";
					String key = "";

					List<PaySetting> settings = paymode.getPaysettings();
					Iterator it = settings.iterator();
					while (it.hasNext()) {
						PaySetting setting = (PaySetting) it.next();
						if (setting.getProperty().equals("partner")) {
							partner = setting.getValue();
						}
						if (setting.getProperty().equals("key")) {
							key = setting.getValue();
						}
					}
					String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?partner="
							+

							partner
							+ "&notify_id="
							+ this.request.getParameter("notify_id");
					String sign = this.request.getParameter("sign");

					String responseTxt = CheckURL.check(alipayNotifyURL);
					System.out.println("responseTxt: " + responseTxt);

					Map params = new HashMap();

					Map requestParams = this.request.getParameterMap();
					for (Iterator iter = requestParams.keySet().iterator(); iter
							.hasNext();) {
						String name = (String) iter.next();
						String[] values = (String[]) requestParams.get(name);
						String valueStr = "";
						for (int i = 0; i < values.length; i++) {
							valueStr = valueStr + values[i] + ",";
						}
						System.out.println(name + " : " + valueStr);
						params.put(name, valueStr);
					}
					String mysign = SignatureHelper_return.sign(params, key);
					System.out.println("mysign: " + mysign);

					System.out.println(mysign + "--------------------" + sign);
					if ((mysign.equals(this.request.getParameter("sign")))
							&& (responseTxt.equals("true"))) {
						System.out.println("success");
						System.out.println(params.get("body"));
						System.out.println(params.get("body"));
						System.out.println("显示订单信息");
						System.out.println(responseTxt);

						brunacc.setStatus("ok");

						Bank bank = brunacc.getBankk();
						Double balance = Double.valueOf(bank.getBalance()
								.doubleValue()
								+ brunacc.getPayin().doubleValue());
						bank.setBalance(balance);
						this.bankService.update(bank);

						this.bankRunAccountService.update(brunacc);

						this.notice.setStatus(true);
						this.notice.setCode(this.request
								.getParameter("trade_status"));
						this.notice.setTitle("提示：");
						this.notice.setBody("充值成功。");
					} else {
						System.out.println("fail");

						this.notice.setStatus(false);
						this.notice.setCode(this.request
								.getParameter("trade_status"));
						this.notice.setTitle("错误：");
						this.notice.setBody("充值失败。");
					}
				}
			} else {
				this.notice.setStatus(false);
				this.notice.setCode(this.request.getParameter("trade_status"));
				this.notice.setTitle("警告：");
				this.notice.setBody("非常操作，充值记录不存在。");
			}
		}
		return "success";
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}
}
