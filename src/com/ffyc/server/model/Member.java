package com.ffyc.server.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ffyc.server.mapper.AddressMapper;
import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.BankMapper;
import com.ffyc.server.mapper.DistrictMapper;
import com.ffyc.server.mapper.GroupMapper;
import com.ffyc.server.mapper.PointMapper;
import com.ffyc.server.utils.BeanFactory;

public class Member implements User, Serializable {
	private static final long serialVersionUID = -7468115216758488107L;
	public static final String DEFAULT_PASSWORD = "123456";
	public static final String STATUS_NORMAL = "normal";
	public static final String STATUS_DELETE = "delete";
	public static final String CTYPE_NORMAL = "normal";

	private String id;
	private String account;
	private String name;
	private String password;
	private String cpassword;
	private String mobile;
	private String gender;
	private Date birthday;
	
	private String bank;
	private Bank bankk;
	private String group;
	private Group groupp;
	private String image;
	private Attachment imagee;
	private String point;
	private Point pointt;
	
	private String activation;
	private String status;
	private String email;
	private String ctype;
	private String nickname;
	private String idcard;
	private Timestamp lastlogin;
	private Integer logincount;
	private Timestamp ctime;
	private Timestamp utime;
	
	private String district;
	private District districtt;
	private String address;
	private String postcode;
	private String telephone;
	private String wechat;
	private String qq;
	private String store;
	
	private List addresses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public boolean isManager() {
		return false;
	}

	@Override
	public boolean isMember() {
		return true;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getPoint() {
		return this.point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getActivation() {
		return this.activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCtype() {
		return this.ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Timestamp getLastlogin() {
		return this.lastlogin;
	}

	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}

	public Integer getLogincount() {
		return this.logincount;
	}

	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}

	public Timestamp getCtime() {
		return this.ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public District getDistrictt() {
		if(StringUtils.isNotEmpty(this.district) && this.districtt == null){
			DistrictMapper districtMapper = BeanFactory.getInstance().getDistrictMapper();
			this.districtt = districtMapper.findById(Integer.valueOf(this.district));
		}
		return districtt;
	}

	public void setDistrictt(District districtt) {
		this.districtt = districtt;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getStore() {
		return this.store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	@Override
	public Timestamp getUtime() {
		return this.utime;
	}

	@Override
	public void setUtime(Timestamp utime) {
		this.utime = utime;

	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public Bank getBankk() {
		if(StringUtils.isNotEmpty(this.bank) && this.bankk == null){
			BankMapper bankMapper = BeanFactory.getInstance().getBankMapper();
			this.bankk = bankMapper.findById(this.bank);
		}
		return bankk;
	}

	public void setBankk(Bank bankk) {
		this.bankk = bankk;
	}

	public Group getGroupp() {
		if(StringUtils.isNotEmpty(this.group) && this.groupp!= null){
			GroupMapper groupMapper = BeanFactory.getInstance().getGroupMapper();
			this.groupp = groupMapper.findById(this.group);
		}
		return groupp;
	}

	public void setGroupp(Group groupp) {
		this.groupp = groupp;
	}

	public Attachment getImagee() {
		if(StringUtils.isNotEmpty(this.image) && this.imagee == null){
			AttachmentMapper attachmentMapper = BeanFactory.getInstance().getAttachmentMapper();
			this.imagee = attachmentMapper.findById(this.image);
		}
		return imagee;
	}

	public void setImagee(Attachment imagee) {
		this.imagee = imagee;
	}

	public Point getPointt() {
		if(StringUtils.isNotEmpty(this.point) && this.pointt == null){
			PointMapper pointMapper = BeanFactory.getInstance().getPointMapper();
			this.pointt = pointMapper.findById(this.point);
		}
		return pointt;
	}

	public void setPointt(Point pointt) {
		this.pointt = pointt;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public List getAddresses() {
		if(this.addresses == null){
			AddressMapper addressMapper = BeanFactory.getInstance().getAddressMapper();
			Address dc =new Address();
			dc.setMember(this.id);
			this.addresses = addressMapper.findByAddress(dc, null, null);
		}
		return addresses;
	}

	public void setAddresses(List addresses) {
		this.addresses = addresses;
	}
	
}
