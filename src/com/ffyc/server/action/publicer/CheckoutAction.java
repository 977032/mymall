package com.ffyc.server.action.publicer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Area;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.model.Spec;
import com.ffyc.server.model.Address;
import com.ffyc.server.utils.Notice;

public class CheckoutAction extends BaseAction {
	private static final long serialVersionUID = -7022458098977117098L;
	private Notice notice = new Notice();
	private String str;
	private Address address = new Address();
	private String pm;
	private Order order = new Order();
	private String oid;

	public String execute()
    throws Exception
  {
    if (StringUtils.isNotEmpty(this.str))
    {
      this.address = this.addressService.findById(this.str);
    }
    else
    {
      this.address.setId(getUuid());
      this.address.setMember(getMemberFromSession().getId());
      this.address.setStatus(Address.STATUS_NORMAL);
      this.addressService.save(this.address);
    }
    Area area = null;
    if (this.address.getDistrict() != null)
    {
      List<Area> list = this.address.getDistrictt().getAreas();
      if ((list != null) && (list.size() > 0)) {
        area = (Area)list.get(0);
      }
    }
    PayMode paymode = this.payModeService.findById(this.pm);
    Order od = (Order)this.session.get("order");
    if (area == null)
    {
      Area dc = new Area();
      dc.setStatus(Area.STATUS_DEFAULT);
      List<Area> list2 = areaService.findByArea(dc, null, null); 
      if ((list2 != null) && (list2.size() > 0)) {
        area = (Area)list2.get(0);
      }
    }
    if ((this.address != null) && (paymode != null) && (od != null))
    {
      this.order.setId(getUuid());
      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
      String code = sdf.format(new Date()) + RandomStringUtils.random(3, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
      System.out.println(code);
      this.order.setCode(code);
      this.order.setSubject("");
      this.order.setBody("");
      this.order.setStatus(Order.STATUS_WAIT_BUYER_PAY);
      this.order.setMember(getMemberFromSession().getId());
      this.order.setCtime(getTimestamp());
      this.order.setAmount(new Double(0.0D));
      this.order.setAddress(this.address.getId());
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
      
      Iterator it = od.getOrderitems().iterator();
      
      Double weight = new Double(0.0D);
      Double amount = new Double(0.0D);
      while (it.hasNext())
      {
        OrderItem oi = (OrderItem)it.next();
        oi.setMember(getMemberFromSession().getId());
        oi.setOrder(this.order.getId());
        try {
			this.orderItemService.save(oi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if ((oi.getSpec() != null) && (oi.getSpecc().getWeight() != null)) {
          weight = Double.valueOf(weight.doubleValue() + oi.getSpecc().getWeight().doubleValue() * oi.getNumber().intValue());
        } else if (oi.getGoodd().getWeight() != null) {
          weight = Double.valueOf(weight.doubleValue() + oi.getGoodd().getWeight().doubleValue() * oi.getNumber().intValue());
        }
        amount = Double.valueOf(amount.doubleValue() + oi.getSubtotal().doubleValue());
        this.order.setSubject(oi.getGoodd().getName() + "...");
        this.order.setBody(oi.getGoodd().getName() + "...");
        

        Integer number = oi.getNumber();
        Spec spec = oi.getSpecc();
        if (spec != null)
        {
          spec.setInventory(Integer.valueOf(spec.getInventory().intValue() - number.intValue()));
          this.specService.update(spec);
        }
        else
        {
          Good good = oi.getGoodd();
          if (good.getInventory().intValue() - number.intValue() >= 0)
          {
            good.setInventory(Integer.valueOf(good.getInventory().intValue() - number.intValue()));
            this.goodService.update(good);
          }
        }
      }
      this.order.setLogicost(area.getLogicost(weight));
      
      this.order.setWeight(weight);
      this.order.setAmount(amount);
      this.order.setActamount(amount);
      this.order.setPaycost(Double.valueOf(paymode.getRate().doubleValue() / 100.0D * amount.doubleValue()));
      this.orderService.update(this.order);
      
      setOid(this.order.getId());
      this.session.remove("order");
      return "success";
    }
    this.notice.setStatus(false);
    this.notice.setCode("cart is null");
    this.notice.setTitle("提示");
    this.notice.setBody("无法完成订单，原因可能是：1、购物车是空的；2、填写的信息有误；3、物流暂时无法配送到您所填写的区域。请联系管理员，感谢您的支持！");
    return "error";
  }

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public String getStr() {
		return this.str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
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
