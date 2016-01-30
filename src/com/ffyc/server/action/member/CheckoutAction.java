package com.ffyc.server.action.member;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.RandomStringUtils;
import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Address;
import com.ffyc.server.model.Area;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.model.PayMode;

public class CheckoutAction extends BaseAction {
	private static final long serialVersionUID = 2875816177218164337L;
	private String str;
	private String la;
	private String pm;
	private Order order = new Order();
	private String oid;

	public String execute() throws Exception {
		Address address = this.addressService.findById(this.str);
		Area area = this.areaService.findById(this.la);
		PayMode paymode = this.payModeService.findById(this.pm);
		if ((address != null) && (area != null) && (paymode != null)) {
			this.order.setId(getUuid());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
			String code = sdf.format(new Date())
					+ RandomStringUtils.random(3, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			System.out.println(code);
			this.order.setCode(code);
			this.order.setSubject("");
			this.order.setBody("");
			this.order.setStatus(Order.STATUS_WAIT_BUYER_PAY);
			this.order.setMember(getMemberFromSession().getId());
			this.order.setCtime(getTimestamp());
			this.order.setAmount(new Double(0.0D));
			this.order.setAddress(address.getId());
			this.order.setArea(area.getId());
			this.order.setPaymode(paymode.getId());
			this.order.setBaojia(new Double(0.0D));
			this.order.setInvoicetitle("");
			this.order.setDuty(new Double(0.0D));
			this.order.setDiscount(new Double(0.0D));
			this.order.setActamount(this.order.getAmount());
			this.order.setLogicost(new Double(0.0D));
			this.order.setWeight(new Double(0.0D));
			this.order.setPaycost(new Double(0.0D));
			this.orderService.save(this.order);
			OrderItem dc = new OrderItem();
			dc.setMember(getMemberFromSession().getId());
			dc.setOrder(null);
			List<OrderItem> list = this.orderItemService.findByOrderItem(dc);

			Double weight = new Double(0.0D);
			Double amount = new Double(0.0D);
			for (int i = 0; i < list.size(); i++) {
				OrderItem oi = (OrderItem) list.get(i);
				oi.setOrderr(this.order);
				oi.setOrder(this.order.getId());
				this.orderItemService.update(oi);
				if ((oi.getGoodd().getFreeshipping() != null)
						&& (oi.getGoodd().getFreeshipping().equals("no"))) {
					if ((oi.getSpecc() != null)
							&& (oi.getSpecc().getWeight() != null)) {
						weight = Double.valueOf(weight.doubleValue()
								+ oi.getSpecc().getWeight().doubleValue()
								* oi.getNumber().intValue());
					} else if (oi.getGoodd().getWeight() != null) {
						weight = Double.valueOf(weight.doubleValue()
								+ oi.getGoodd().getWeight().doubleValue()
								* oi.getNumber().intValue());
					}
				}
				amount = Double.valueOf(amount.doubleValue()
						+ oi.getSubtotal().doubleValue());
				this.order.setSubject(oi.getGoodd().getName() + "...");
				this.order.setBody(oi.getGoodd().getName() + "...");
			}
			this.order.setLogicost(area.getLogicost(weight));
			this.order.setWeight(weight);
			this.order.setAmount(amount);
			this.order.setActamount(amount);
			this.order.setPaycost(Double.valueOf(paymode.getRate()
					.doubleValue() / 100.0D * amount.doubleValue()));
			this.orderService.update(this.order);

			setOid(this.order.getId());
			return "success";
		}
		return "error";
	}

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getLa() {
		return this.la;
	}

	public void setLa(String la) {
		this.la = la;
	}

	public String getPm() {
		return this.pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
}
