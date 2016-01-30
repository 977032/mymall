package com.ffyc.server.action.payment.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.dc.OrderDC;
import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.model.PaySetting;
import com.ffyc.server.utils.alipay.CheckURL;
import com.ffyc.server.utils.alipay.SignatureHelper_return;

public class NotifyAction 
extends BaseAction
{
 private static final long serialVersionUID = 3716120041447914011L;
 private String result = "failed";
 
 public String execute()
   throws Exception
 {
   String out_trade_no = this.request.getParameter("out_trade_no");
   OrderDC dc = new OrderDC();
   dc.setCode(out_trade_no);
   
   Order order = null ;
   List<Order> orders = this.orderService.findByOrder(dc);
   if(orders!=null && orders.size()>0){
	   order = orders.get(0);
   }
   if (order != null)
   {
     PayMode paymode = order.getPaymodee();
     if ((paymode != null) && (paymode.getPmtype().equals("alipay")))
     {
       String partner = "";
       String key = "";
       
       List<PaySetting> settings = paymode.getPaysettings();
       Iterator it = settings.iterator();
       while (it.hasNext())
       {
         PaySetting setting = (PaySetting)it.next();
         if (setting.getProperty().equals("partner")) {
           partner = setting.getValue();
         }
         if (setting.getProperty().equals("key")) {
           key = setting.getValue();
         }
       }
       String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?partner=" + 
       
         partner + 
         "&notify_id=" + 
         this.request.getParameter("notify_id");
       String sign = this.request.getParameter("sign");
       

       String responseTxt = CheckURL.check(alipayNotifyURL);
       System.out.println("responseTxt: " + responseTxt);
       
       Map params = new HashMap();
       
       Map requestParams = this.request.getParameterMap();
       for (Iterator iter = requestParams.keySet().iterator(); iter
             .hasNext();)
       {
         String name = (String)iter.next();
         String[] values = (String[])requestParams.get(name);
         String valueStr = "";
         for (int i = 0; i < values.length; i++) {
           valueStr = 
             valueStr + values[i] + ",";
         }
         System.out.println(name + " : " + valueStr);
         params.put(name, valueStr);
       }
       String mysign = SignatureHelper_return.sign(params, key);
       
       System.out.println(mysign + "--------------------" + sign);
       
       String trade_status = order.getStatus();
       if ((mysign.equals(this.request.getParameter("sign"))) && (responseTxt.equals("true")) && 
         (!trade_status.equals("WAIT_BUYER_PAY"))) {
         if (!trade_status.equals("WAIT_SELLER_SEND_GOODS")) {
           if (!trade_status.equals("WAIT_BUYER_CONFIRM_GOODS")) {
             trade_status.equals("TRADE_FINISHED");
           }
         }
       }
     }
   }
   return "error";
 }
}

