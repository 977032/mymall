package com.ffyc.server.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ffyc.server.mapper.AddressMapper;
import com.ffyc.server.mapper.AreaMapper;
import com.ffyc.server.mapper.AttachmentMapper;
import com.ffyc.server.mapper.BankMapper;
import com.ffyc.server.mapper.BankRunAccountMapper;
import com.ffyc.server.mapper.BrandMapper;
import com.ffyc.server.mapper.CategoryMapper;
import com.ffyc.server.mapper.ChannelContentMapper;
import com.ffyc.server.mapper.ChannelMapper;
import com.ffyc.server.mapper.CommentMapper;
import com.ffyc.server.mapper.ConfigMapper;
import com.ffyc.server.mapper.DistrictMapper;
import com.ffyc.server.mapper.DocumentCategoryMapper;
import com.ffyc.server.mapper.DocumentMapper;
import com.ffyc.server.mapper.EvaluateMapper;
import com.ffyc.server.mapper.ExtraAttrMapper;
import com.ffyc.server.mapper.ExtraAttrValueMapper;
import com.ffyc.server.mapper.GoodMapper;
import com.ffyc.server.mapper.GoodTypeMapper;
import com.ffyc.server.mapper.GroupMapper;
import com.ffyc.server.mapper.LocaleMapper;
import com.ffyc.server.mapper.LogisticMapper;
import com.ffyc.server.mapper.ManagerGroupMapper;
import com.ffyc.server.mapper.ManagerMapper;
import com.ffyc.server.mapper.MemberMapper;
import com.ffyc.server.mapper.MessageMapper;
import com.ffyc.server.mapper.OrderItemMapper;
import com.ffyc.server.mapper.OrderMapper;
import com.ffyc.server.mapper.PayModeMapper;
import com.ffyc.server.mapper.PaySettingMapper;
import com.ffyc.server.mapper.PermissionMapper;
import com.ffyc.server.mapper.PointMapper;
import com.ffyc.server.mapper.PointRunAccountMapper;
import com.ffyc.server.mapper.RegionMapper;
import com.ffyc.server.mapper.SpecItemMapper;
import com.ffyc.server.mapper.SpecMapper;
import com.ffyc.server.mapper.SpecValueMapper;
import com.ffyc.server.mapper.StoreMapper;
import com.ffyc.server.mapper.ThirdPartMapper;
import com.ffyc.server.mapper.UserMapper;
import com.ffyc.server.mapper.VerifyCodeMapper;

public class BeanFactory {
	private static BeanFactory instance;
	private static ApplicationContext beanFactory;

	private static AddressMapper addressMapper;
	private static AreaMapper areaMapper;
	private static AttachmentMapper attachmentMapper;
	private static BankMapper bankMapper;
	private static BankRunAccountMapper bankRunAccountMapper;
	private static BrandMapper brandMapper;
	private static CategoryMapper categoryMapper;
	private static ChannelContentMapper channelContentMapper;
	private static ChannelMapper channelMapper;
	private static CommentMapper commentMapper;
	private static ConfigMapper configMapper;
	private static DistrictMapper districtMapper;
	private static DocumentCategoryMapper documentCategoryMapper;
	private static DocumentMapper documentMapper;
	private static EvaluateMapper evaluateMapper;
	private static ExtraAttrMapper extraAttrMapper;
	private static ExtraAttrValueMapper extraAttrValueMapper;
	private static GoodMapper goodMapper;
	private static GoodTypeMapper goodTypeMapper;

	private static GroupMapper groupMapper;
	private static LocaleMapper localeMapper;
	private static LogisticMapper logisticMapper;
	private static ManagerGroupMapper managerGroupMapper;
	private static ManagerMapper managerMapper;
	private static MemberMapper memberMapper;
	private static MessageMapper messageMapper;
	private static OrderItemMapper orderItemMapper;
	private static OrderMapper orderMapper;
	private static PayModeMapper payModeMapper;

	private static PermissionMapper permissionMapper;
	private static PointMapper pointMapper;
	private static PointRunAccountMapper pointRunAccountMapper;
	private static RegionMapper regionMapper;
	private static PaySettingMapper paySettingMapper;
	private static SpecItemMapper specItemMapper;
	private static SpecMapper specMapper;
	private static SpecValueMapper specValueMapper;
	private static StoreMapper storeMapper;

	private static ThirdPartMapper thirdPartMapper;
	private static UserMapper userMapper;
	private static VerifyCodeMapper verifyCodeMapper;

	private BeanFactory() {
		beanFactory = new ClassPathXmlApplicationContext(
				"spring-custom-config.xml");
		addressMapper = (AddressMapper) getBean("addressMapper");
		areaMapper = (AreaMapper) getBean("areaMapper");
		attachmentMapper = (AttachmentMapper) getBean("attachmentMapper");
		bankMapper = (BankMapper) getBean("bankMapper");
		bankRunAccountMapper = (BankRunAccountMapper) getBean("bankRunAccountMapper");
		brandMapper = (BrandMapper) getBean("brandMapper");
		categoryMapper = (CategoryMapper) getBean("categoryMapper");
		channelContentMapper = (ChannelContentMapper) getBean("channelContentMapper");
		channelMapper = (ChannelMapper) getBean("channelMapper");
		commentMapper = (CommentMapper) getBean("commentMapper");
		configMapper = (ConfigMapper) getBean("configMapper");
		districtMapper = (DistrictMapper) getBean("districtMapper");
		documentCategoryMapper = (DocumentCategoryMapper) getBean("documentCategoryMapper");
		documentMapper = (DocumentMapper) getBean("documentMapper");
		evaluateMapper = (EvaluateMapper) getBean("evaluateMapper");
		extraAttrMapper = (ExtraAttrMapper) getBean("extraAttrMapper");
		extraAttrValueMapper = (ExtraAttrValueMapper) getBean("extraAttrValueMapper");
		goodMapper = (GoodMapper) getBean("goodMapper");
		goodTypeMapper = (GoodTypeMapper) getBean("goodTypeMapper");

		groupMapper = (GroupMapper) getBean("groupMapper");
		localeMapper = (LocaleMapper) getBean("localeMapper");
		logisticMapper = (LogisticMapper) getBean("logisticMapper");
		managerGroupMapper = (ManagerGroupMapper) getBean("managerGroupMapper");
		managerMapper = (ManagerMapper) getBean("managerMapper");
		memberMapper = (MemberMapper) getBean("memberMapper");
		messageMapper = (MessageMapper) getBean("messageMapper");
		orderItemMapper = (OrderItemMapper) getBean("orderItemMapper");
		orderMapper = (OrderMapper) getBean("orderMapper");
		payModeMapper = (PayModeMapper) getBean("payModeMapper");

		permissionMapper = (PermissionMapper) getBean("permissionMapper");
		pointMapper = (PointMapper) getBean("pointMapper");
		pointRunAccountMapper = (PointRunAccountMapper) getBean("pointRunAccountMapper");
		regionMapper = (RegionMapper) getBean("regionMapper");
		paySettingMapper = (PaySettingMapper) getBean("paySettingMapper");
		specItemMapper = (SpecItemMapper) getBean("specItemMapper");
		specMapper = (SpecMapper) getBean("specMapper");
		specValueMapper = (SpecValueMapper) getBean("specValueMapper");
		storeMapper = (StoreMapper) getBean("storeMapper");

		thirdPartMapper = (ThirdPartMapper) getBean("thirdPartMapper");
		userMapper = (UserMapper) getBean("userMapper");
		verifyCodeMapper = (VerifyCodeMapper) getBean("verifyCodeMapper");
	}

	public static synchronized BeanFactory getInstance() {
		if (instance == null) {
			instance = new BeanFactory();
		}
		return instance;
	}

	private Object getBean(String name) {
		return beanFactory.getBean(name);
	}

	public AddressMapper getAddressMapper() {
		return addressMapper;
	}

	public AreaMapper getAreaMapper() {
		return areaMapper;
	}

	public AttachmentMapper getAttachmentMapper() {
		return attachmentMapper;
	}

	public BankMapper getBankMapper() {
		return bankMapper;
	}

	public BankRunAccountMapper getBankRunAccountMapper() {
		return bankRunAccountMapper;
	}

	public BrandMapper getBrandMapper() {
		return brandMapper;
	}

	public CategoryMapper getCategoryMapper() {
		return categoryMapper;
	}

	public ChannelContentMapper getChannelContentMapper() {
		return channelContentMapper;
	}

	public ChannelMapper getChannelMapper() {
		return channelMapper;
	}

	public CommentMapper getCommentMapper() {
		return commentMapper;
	}

	public ConfigMapper getConfigMapper() {
		return configMapper;
	}

	public DistrictMapper getDistrictMapper() {
		return districtMapper;
	}

	public DocumentCategoryMapper getDocumentCategoryMapper() {
		return documentCategoryMapper;
	}

	public DocumentMapper getDocumentMapper() {
		return documentMapper;
	}

	public EvaluateMapper getEvaluateMapper() {
		return evaluateMapper;
	}

	public ExtraAttrMapper getExtraAttrMapper() {
		return extraAttrMapper;
	}

	public ExtraAttrValueMapper getExtraAttrValueMapper() {
		return extraAttrValueMapper;
	}

	public GoodMapper getGoodMapper() {
		return goodMapper;
	}

	public GoodTypeMapper getGoodTypeMapper() {
		return goodTypeMapper;
	}

	public GroupMapper getGroupMapper() {
		return groupMapper;
	}

	public LocaleMapper getLocaleMapper() {
		return localeMapper;
	}

	public LogisticMapper getLogisticMapper() {
		return logisticMapper;
	}

	public ManagerGroupMapper getManagerGroupMapper() {
		return managerGroupMapper;
	}

	public ManagerMapper getManagerMapper() {
		return managerMapper;
	}

	public MemberMapper getMemberMapper() {
		return memberMapper;
	}

	public MessageMapper getMessageMapper() {
		return messageMapper;
	}

	public OrderItemMapper getOrderItemMapper() {
		return orderItemMapper;
	}

	public OrderMapper getOrderMapper() {
		return orderMapper;
	}

	public PayModeMapper getPayModeMapper() {
		return payModeMapper;
	}

	public PermissionMapper getPermissionMapper() {
		return permissionMapper;
	}

	public PointMapper getPointMapper() {
		return pointMapper;
	}

	public PointRunAccountMapper getPointRunAccountMapper() {
		return pointRunAccountMapper;
	}

	public RegionMapper getRegionMapper() {
		return regionMapper;
	}

	public PaySettingMapper getPaySettingMapper() {
		return paySettingMapper;
	}

	public SpecItemMapper getSpecItemMapper() {
		return specItemMapper;
	}

	public SpecMapper getSpecMapper() {
		return specMapper;
	}

	public SpecValueMapper getSpecValueMapper() {
		return specValueMapper;
	}

	public StoreMapper getStoreMapper() {
		return storeMapper;
	}

	public ThirdPartMapper getThirdPartMapper() {
		return thirdPartMapper;
	}

	public UserMapper getUserMapper() {
		return userMapper;
	}

	public VerifyCodeMapper getVerifyCodeMapper() {
		return verifyCodeMapper;
	}
}
