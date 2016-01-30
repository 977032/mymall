package com.ffyc.server.utils.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ffyc.server.model.Order;
import com.ffyc.server.model.PayMode;
import com.ffyc.server.model.PaySetting;
import com.ffyc.server.utils.alipay.Md5Encrypt;

public class PaymentUrl 
{
	  public String getUrlparam(Order order, String preUrl)
	  {
	    String urlparam = "";
	    
	    String paygateway = "";
	    String service = "";
	    String sign_type = "";
	    String input_charset = "utf-8";
	    String payment_type = "1";
	    String partner = "";
	    String key = "";
	    String seller_email = "";
	    
	    String out_trade_no = order.getCode();
	    String subject = order.getSubject();
	    String price = order.getActamount().toString();
	    String quantity = "1";
	    String logistics_type = "EXPRESS";
	    String logistics_fee = order.getLogicost().toString();
	    System.out.println(logistics_fee);
	    String logistics_payment = "BUYER_PAY";
	    
	    String body = order.getBody();
	    
	    String return_url = preUrl + "/alipay_return.htm";
	    String notify_url = preUrl + "/alipay_notify.htm";
	    
	    String show_url = preUrl + "/member/orderitems.htm?oid=" + order.getId();
	    String buyer_email = "";
	    String discount = "0";
	    
	    PayMode paymode = order.getPaymodee();
	    if ((paymode != null) && ((paymode.getPmtype().equals(PayMode.PMTYPE_ALIPAY)) || (paymode.getPmtype().equals(PayMode.PMTYPE_ALIPAY))))
	    {
	      List<PaySetting> list = paymode.getPaysettings();
	      Iterator it = list.iterator();
	      while (it.hasNext())
	      {
	        PaySetting setting = (PaySetting)it.next();
	        if (setting.getProperty().equals("paygateway")) {
	          paygateway = setting.getValue();
	        }
	        if (setting.getProperty().equals("service")) {
	          service = setting.getValue();
	        }
	        if (setting.getProperty().equals("sign_type")) {
	          sign_type = setting.getValue();
	        }
	        if (setting.getProperty().equals("partner")) {
	          partner = setting.getValue();
	        }
	        if (setting.getProperty().equals("key")) {
	          key = setting.getValue();
	        }
	        if (setting.getProperty().equals("seller_email")) {
	          seller_email = setting.getValue();
	        }
	      }
	    }
	    if ((!paygateway.equals("")) && (!service.equals("")) && (!sign_type.equals("")) && (!out_trade_no.equals("")) && (!partner.equals("")) && 
	      (!key.equals("")) && (!seller_email.equals(""))) {
	      if (order.getAddresss() != null)
	      {
	        String receive_name = order.getAddresss().getName();
	        String receive_address = order.getAddresss().getAddress();
	        String receive_zip = order.getAddresss().getPostcode();
	        String receive_phone = order.getAddresss().getTelephone();
	        String receive_mobile = order.getAddresss().getMobile();
	        
	        urlparam = CreateUrl(paygateway, service, sign_type, out_trade_no, input_charset, partner, 
	          key, seller_email, body, subject, price, quantity, show_url, payment_type, discount, 
	          logistics_type, logistics_fee, logistics_payment, return_url, notify_url, 
	          buyer_email, receive_name, receive_address, receive_zip, receive_phone, receive_mobile);
	      }
	      else
	      {
	        urlparam = CreateUrl(paygateway, service, sign_type, out_trade_no, input_charset, partner, 
	          key, seller_email, body, subject, price, quantity, show_url, payment_type, discount, 
	          logistics_type, logistics_fee, logistics_payment, return_url, notify_url, 
	          buyer_email, "", "", "", "", "");
	      }
	    }

	    return urlparam;
	  }
	  
	  public static String CreateUrl(String paygateway, String service, String sign_type, String out_trade_no, String input_charset, String partner, String key, String seller_email, String body, String subject, String price, String quantity, String show_url, String payment_type, String discount, String logistics_type, String logistics_fee, String logistics_payment, String return_url, String notify_url, String buyer_email, String receive_name, String receive_address, String receive_zip, String receive_phone, String receive_mobile)
	  {
	    Map params = new HashMap();
	    params.put("service", service);
	    params.put("out_trade_no", out_trade_no);
	    params.put("show_url", show_url);
	    params.put("quantity", quantity);
	    params.put("partner", partner);
	    params.put("payment_type", payment_type);
	    params.put("discount", discount);
	    params.put("body", body);
	    params.put("notify_url", notify_url);
	    params.put("price", price);
	    params.put("return_url", return_url);
	    params.put("seller_email", seller_email);
	    params.put("logistics_type", logistics_type);
	    params.put("logistics_fee", logistics_fee);
	    params.put("logistics_payment", logistics_payment);
	    params.put("subject", subject);
	    params.put("_input_charset", input_charset);
	    params.put("buyer_email", buyer_email);
	    params.put("receive_name", receive_name);
	    params.put("receive_address", receive_address);
	    params.put("receive_zip", receive_zip);
	    params.put("receive_phone", receive_phone);
	    params.put("receive_mobile", receive_mobile);
	    String prestr = "";
	    
	    prestr = prestr + key;
	    

	    String sign = Md5Encrypt.md5(getContent(params, key));
	    
	    String parameter = "";
	    parameter = parameter + paygateway;
	    
	    List keys = new ArrayList(params.keySet());
	    for (int i = 0; i < keys.size(); i++)
	    {
	      String value = (String)params.get(keys.get(i));
	      if ((value != null) && (value.trim().length() != 0)) {
	        try
	        {
	          parameter = 
	            parameter + keys.get(i) + "=" + URLEncoder.encode(value, input_charset) + "&";
	        }
	        catch (UnsupportedEncodingException e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    parameter = parameter + "sign=" + sign + "&sign_type=" + sign_type;
	    System.out.println(parameter);
	    
	    return parameter;
	  }
	  
	  public static String CreateUrl1(String paygateway, String service, String sign_type, String input_charset, String partner, String key, String trade_no, String transport_type)
	  {
	    Map params = new HashMap();
	    params.put("service", service);
	    params.put("partner", partner);
	    params.put("trade_no", trade_no);
	    params.put("transport_type", transport_type);
	    params.put("_input_charset", input_charset);
	    
	    String prestr = "";
	    
	    prestr = prestr + key;
	    

	    String sign = Md5Encrypt.md5(getContent(params, key));
	    
	    String parameter = "";
	    parameter = parameter + paygateway;
	    
	    List keys = new ArrayList(params.keySet());
	    for (int i = 0; i < keys.size(); i++)
	    {
	      String value = (String)params.get(keys.get(i));
	      if ((value != null) && (value.trim().length() != 0)) {
	        try
	        {
	          parameter = 
	            parameter + keys.get(i) + "=" + URLEncoder.encode(value, input_charset) + "&";
	        }
	        catch (UnsupportedEncodingException e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    parameter = parameter + "sign=" + sign + "&sign_type=" + sign_type;
	    System.out.println(parameter);
	    
	    return parameter;
	  }
	  
	  public static String CreateUrl2(String paygateway, String service, String sign_type, String input_charset, String partner, String key, String trade_no, String logistics_name, String invoice_no, String transport_type)
	  {
	    Map params = new HashMap();
	    params.put("service", service);
	    params.put("partner", partner);
	    params.put("trade_no", trade_no);
	    params.put("logistics_name", logistics_name);
	    params.put("invoice_no", invoice_no);
	    params.put("transport_type", transport_type);
	    params.put("_input_charset", input_charset);
	    
	    String prestr = "";
	    
	    prestr = prestr + key;
	    

	    String sign = Md5Encrypt.md5(getContent(params, key));
	    
	    String parameter = "";
	    parameter = parameter + paygateway;
	    
	    List keys = new ArrayList(params.keySet());
	    for (int i = 0; i < keys.size(); i++)
	    {
	      String value = (String)params.get(keys.get(i));
	      if ((value != null) && (value.trim().length() != 0)) {
	        try
	        {
	          parameter = 
	            parameter + keys.get(i) + "=" + URLEncoder.encode(value, input_charset) + "&";
	        }
	        catch (UnsupportedEncodingException e)
	        {
	          e.printStackTrace();
	        }
	      }
	    }
	    parameter = parameter + "sign=" + sign + "&sign_type=" + sign_type;
	    
	    return parameter;
	  }
	  
	  private static String getContent(Map params, String privateKey)
	  {
	    List keys = new ArrayList(params.keySet());
	    Collections.sort(keys);
	    
	    String prestr = "";
	    
	    boolean first = true;
	    for (int i = 0; i < keys.size(); i++)
	    {
	      String key = (String)keys.get(i);
	      String value = (String)params.get(key);
	      if ((value != null) && (value.trim().length() != 0)) {
	        if (first)
	        {
	          prestr = prestr + key + "=" + value;
	          first = false;
	        }
	        else
	        {
	          prestr = prestr + "&" + key + "=" + value;
	        }
	      }
	    }
	    return prestr + privateKey;
	  }
	}

