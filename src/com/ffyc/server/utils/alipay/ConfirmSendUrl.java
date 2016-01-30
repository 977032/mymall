package com.ffyc.server.utils.alipay;

import java.util.Iterator;

import java.util.List;

import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.model.PaySetting;

public class ConfirmSendUrl {
	public String Confirm(Order order) throws Exception {
		String ItemUrl = "";
		String paygateway = "https://www.alipay.com/cooperate/gateway.do?";
		String service = "send_goods_confirm_by_platform";
		String sign_type = "MD5";
		String input_charset = "UTF-8";

		String partner = "";
		String key = "";

		PayMode paymode = order.getPaymodee();
		if ((paymode != null)
				&& ((paymode.getPmtype().equals("alipay")) || (paymode
						.getPmtype().equals("alipay")))) {
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
		}
		String trade_no = order.getCode();
		String transport_type = "";

		transport_type = "EXPRESS";
		ItemUrl = PaymentUrl.CreateUrl1(paygateway, service, sign_type,
				input_charset, partner, key, trade_no, transport_type);

		Dom4j dom4 = new Dom4j();
		String result = dom4.DomXml(ItemUrl);

		System.out.println(result);

		return result;
	}
}
