package com.ffyc.server.action.publicer;

import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.Spec;
import com.ffyc.server.model.SpecValue;
import com.ffyc.server.utils.Notice;

public class AddToCartAction extends BaseAction {

	private static final long serialVersionUID = -5417497677932230086L;
	private Notice notice = new Notice();
	private String gid;
	private String sc;
	private Integer num;

	public String execute() throws Exception {
		Good good = this.goodService.findById(this.gid);
		if (good != null) {
			OrderItem oi = new OrderItem();
			oi.setId(getUuid());
			oi.setGoodd(good);
			oi.setGood(good.getId());
			oi.setMemberr(getMemberFromSession());
			Double unitprice = new Double(0.0D);
			if (StringUtils.isNotEmpty(this.sc)) {
				Spec spec = this.specService.findById(this.sc);
				Iterator it = spec.getSpecvalues().iterator();
				while (it.hasNext()) {
					SpecValue sv = (SpecValue) it.next();
					System.out.println(sv.getSpecitemm().getName() + ":"
							+ sv.getName());
				}
				unitprice = spec.getPrice();
				oi.setSpecc(spec);
			} else {
				unitprice = good.getPrice();
			}
			if ((unitprice == null) || (unitprice.doubleValue() <= 0.0D)) {
				this.notice.setCode("CART_NOT_PRICE");
				this.notice.setStatus(false);
				this.notice.setTitle("商品价格错误");
				this.notice.setBody("商品没有价格，或价格错误。");
				return "error";
			}
			oi.setUnitprice(unitprice);
			oi.setNumber(this.num);

			BigDecimal b = new BigDecimal(unitprice.doubleValue()
					* this.num.intValue());
			Double d = Double.valueOf(b.setScale(2, 4).doubleValue());

			oi.setSubtotal(d);
			oi.setDiscount(new Double(0.0D));
			oi.setActsum(oi.getSubtotal());
			oi.setIspay("no");
			oi.setStatus(OrderItem.STATUS_NORMAL);
			oi.setCtime(getTimestamp());

			Order order = (Order) this.session.get("order");
			System.out.println("取得订单Session。");
			if (order == null) {
				order = new Order();
				System.out.println("订单Session是空的，新建一个");
			}
			order.getOrderitems().add(oi);
			System.out.println("加入订单，" + oi.getGoodd().getName());
			this.session.put("order", order);
			return "success";
		}
		return "success";
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getSc() {
		return this.sc;
	}

	public void setSc(String sc) {
		this.sc = sc;
	}

	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
