package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.dc.BankRunAccountDC;
import com.ffyc.server.dc.PaySettingDC;
import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.BankRunAccountMapper;
import com.ffyc.server.mapper.OrderMapper;
import com.ffyc.server.mapper.PaySettingMapper;
import com.ffyc.server.utils.BeanFactory;

public class PayMode implements Serializable {

	private String id;
	private String image;
	private Attachment imagee;
	private Attachment vimage;
	private String pmtype;
	private String name;
	private String intro;
	private Double rate;
	private Double focrating;
	private Integer csort;
	
	private List<Order> orders;
	private List<PaySetting> paysettings;
	private List<BankRunAccount> brunaccs;
	
	public static final String PMTYPE_ALIPAY = "alipay";
	public static final String PMTYPE_OTHER = "other";

	public PayMode() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Attachment getImagee() {
		if (StringUtils.isNotEmpty(this.image) && this.image != null) {
			AttachmentMapper attachmentMapper = BeanFactory.getInstance()
					.getAttachmentMapper();
			return attachmentMapper.findById(this.image);
		}
		return null;
	}

	public void setImagee(Attachment imagee) {
		this.imagee = imagee;
	}

	public Attachment getVimage() {
		if (this.image != null) {
			AttachmentMapper attachmentMapper = BeanFactory.getInstance()
					.getAttachmentMapper();
			return attachmentMapper.findById(this.image);
		}
		Attachment i = new Attachment();
		i.setPath("/images/sys");
		i.setName("nopic.jpg");
		return i;
	}

	public void setVimage(Attachment vimage) {
		this.vimage = vimage;
	}

	public String getPmtype() {
		return pmtype;
	}

	public void setPmtype(String pmtype) {
		this.pmtype = pmtype;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Double getRate() {
		return this.rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getFocrating() {
		return this.focrating;
	}

	public void setFocrating(Double focrating) {
		this.focrating = focrating;
	}

	public Integer getCsort() {
		return this.csort;
	}

	public void setCsort(Integer csort) {
		this.csort = csort;
	}

	public List<Order> getOrders() {
		if(this.orders == null){
			OrderMapper orderMapper = BeanFactory.getInstance().getOrderMapper();
			Order dc = new Order();
			dc.setPaymode(id);
			this.orders = orderMapper.findByOrder(dc, null, null);
		}
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<PaySetting> getPaysettings() {
		if(this.paysettings == null){
			PaySettingMapper paySettingMapper = BeanFactory.getInstance().getPaySettingMapper();
			PaySettingDC dc = new PaySettingDC();
			dc.setPaymode(id);
			this.paysettings = paySettingMapper.findByPaySetting(dc);
		}
		return paysettings;
	}

	public void setPaysettings(List<PaySetting> paysettings) {
		this.paysettings = paysettings;
	}

	public List<BankRunAccount> getBrunaccs() {
		if(this.brunaccs == null){
			BankRunAccountMapper bankRunAccountMapper = BeanFactory.getInstance().getBankRunAccountMapper();
			BankRunAccountDC dc = new BankRunAccountDC();
			dc.setPaymode(id);
			this.brunaccs = bankRunAccountMapper.findByBankRunAccount(dc, null, null);
		}
		return brunaccs;
	}

	public void setBrunaccs(List<BankRunAccount> brunaccs) {
		this.brunaccs = brunaccs;
	}


}
