package com.ffyc.server.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.ffyc.server.model.Category;
import com.ffyc.server.model.ChannelContent;
import com.ffyc.server.model.Config;
import com.ffyc.server.model.Document;
import com.ffyc.server.model.DocumentCategory;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Locale;
import com.ffyc.server.model.Manager;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.Order;
import com.ffyc.server.service.AddressService;
import com.ffyc.server.service.AreaService;
import com.ffyc.server.service.AttachmentService;
import com.ffyc.server.service.BankRunAccountService;
import com.ffyc.server.service.BankService;
import com.ffyc.server.service.BrandService;
import com.ffyc.server.service.CategoryService;
import com.ffyc.server.service.ChannelContentService;
import com.ffyc.server.service.ChannelService;
import com.ffyc.server.service.CommentService;
import com.ffyc.server.service.ConfigService;
import com.ffyc.server.service.DistrictService;
import com.ffyc.server.service.DocumentCategoryService;
import com.ffyc.server.service.DocumentService;
import com.ffyc.server.service.EvaluateService;
import com.ffyc.server.service.ExtraAttrService;
import com.ffyc.server.service.ExtraAttrValueService;
import com.ffyc.server.service.GoodService;
import com.ffyc.server.service.GoodTypeService;
import com.ffyc.server.service.GroupService;
import com.ffyc.server.service.LocaleService;
import com.ffyc.server.service.LogisticService;
import com.ffyc.server.service.ManagerGroupService;
import com.ffyc.server.service.ManagerService;
import com.ffyc.server.service.MemberService;
import com.ffyc.server.service.OrderItemService;
import com.ffyc.server.service.OrderService;
import com.ffyc.server.service.PayModeService;
import com.ffyc.server.service.PermissionService;
import com.ffyc.server.service.PointRunAccountService;
import com.ffyc.server.service.PointService;
import com.ffyc.server.service.PaySettingService;
import com.ffyc.server.service.SpecItemService;
import com.ffyc.server.service.SpecService;
import com.ffyc.server.service.SpecValueService;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.PaginationSupport;
import com.mchange.v2.codegen.bean.Property;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SuperAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware, SessionAware {
	private static final long serialVersionUID = -1170067112876395839L;
	protected Logger logger = Logger.getLogger(getClass());
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected Notice notice = new Notice();
	protected String funccode;
	protected String funcaction;
	protected String funcname;
	
	protected String l;
	protected Locale defLocale = null;
	protected String template;
	protected List caterootlist;

	protected Order cart = new Order();
	protected List<String> searchKeyList;
	protected Map map = new HashMap();
	protected Map session = ActionContext.getContext().getSession();

	@Autowired
	protected AttachmentService attachmentService;
	@Autowired
	protected BankService bankService;
	@Autowired
	protected BrandService brandService;
	@Autowired
	protected BankRunAccountService bankRunAccountService;
	@Autowired
	protected CategoryService categoryService;
	@Autowired
	protected ChannelService channelService;
	@Autowired
	protected ChannelContentService channelContentService;
	@Autowired
	protected CommentService commentService;
	@Autowired
	protected ConfigService configService;
	@Autowired
	protected DistrictService districtService;
	@Autowired
	protected DocumentCategoryService documentCategoryService;
	@Autowired
	protected DocumentService documentService;
	@Autowired
	protected ExtraAttrValueService extraAttrValueService;
	@Autowired
	protected EvaluateService evaluateService;
	@Autowired
	protected ExtraAttrService extraAttrService;
	@Autowired
	protected GoodService goodService;
	@Autowired
	protected GroupService groupService;
	@Autowired
	protected GoodTypeService goodTypeService;
	@Autowired
	protected LocaleService localeService;
	@Autowired
	protected LogisticService logisticService;
	@Autowired
	protected AreaService areaService;
	@Autowired
	protected MemberService memberService;
	@Autowired
	protected ManagerGroupService managerGroupService;
	@Autowired
	protected PermissionService permissionService;
	@Autowired
	protected OrderItemService orderItemService;
	@Autowired
	protected OrderService orderService;
	@Autowired
	protected PayModeService payModeService;
	@Autowired
	protected PointService pointService;
	@Autowired
	protected PointRunAccountService pointRunAccountService;
	@Autowired
	protected PaySettingService paySettingService;
	@Autowired
	protected SpecService specService;
	@Autowired
	protected SpecItemService specItemService;
	@Autowired
	protected SpecValueService specValueService;
	@Autowired
	protected AddressService addressService;
	@Autowired
	protected ManagerService managerService;

	public String getL() {
		return this.l;
	}

	public void setL(String l) {
		this.l = l;
	}

	public ManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(ManagerService managerService) {
		this.managerService.setSession(this.session);
		this.managerService = managerService;
	}
	
	public MemberService getMemberService() {
		return memberService;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService.setSession(this.session);
		this.memberService = memberService;
	}

	public Locale getDefLocale() {
		this.defLocale = new Locale();
		this.defLocale.setId("zh_CN");
		this.defLocale.setName("简体中文");
		this.session.put("locale", this.defLocale);
		return this.defLocale;
		/*
		if (StringUtils.isNotEmpty(this.l)) {
			this.defLocale = localeService.findById(this.l);
			if (this.defLocale != null) {
				this.session.put("locale", this.defLocale);
				return this.defLocale;
			}
		}
		this.defLocale = ((Locale) this.session.get("locale"));
		if (this.defLocale == null) {
			Config config = configService.findByProperty("defaultLocale");
			if (config != null) {
				String defaultLocale = config.getValue();
				this.defLocale = this.localeService.findById(defaultLocale);
				this.session.put("locale", this.defLocale);
				return this.defLocale;
			}
		}*/
	}

	public String getTemplate() {
		return this.template;
	}

	public List getCaterootlist() {
	    this.caterootlist = this.categoryService.findAllRoot(getDefLocale());
	    return this.caterootlist;
	}

	public void setCaterootlist(List caterootlist) {
		this.caterootlist = caterootlist;
	}

	protected String siteName = "";
	protected String siteTitle = "";
	protected String siteCopyright = "";

	public String getSiteName() {

		Config config = this.configService.findByProperty("siteName");
		this.siteName = config.getValue();
		return this.siteName;

	}

	public String getSiteTitle() {
		Config config = this.configService.findByProperty("siteTitle");
		try {
			this.siteTitle = (config.getValue() + " - Powered by Ffyc");
		} catch (Exception localException) {
		}
		return this.siteTitle;
	}

	public String getSiteCopyright() {
		Config config = this.configService.findByProperty("siteCopyright");
		try {
			this.siteCopyright = (config.getValue() + " - Powered by <a href='http://www.ffyctech.com' target='_blank'>Ffyc</a>");
		} catch (Exception localException) {
		}
		return this.siteCopyright;
	}

	public Order getCart() {
		this.cart = ((Order) ActionContext.getContext().getSession()
				.get("order"));
		if (this.cart != null) {
			return this.cart;
		}
		Order os = new Order();
		this.session.put("order", os);
		return os;
	}

	public List<String> getSearchKeyList() {
		Config config = this.configService.findByProperty("searchKeyList");
		String[] c = config.getValue().split(",");
		this.searchKeyList = Arrays.asList(c);
		return this.searchKeyList;
	}

	public Map getMap() {
		return this.map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	protected Map GlobalChannelContent(Map map) {
       
		List gcclist = this.channelContentService.findAllGlobal(getDefLocale());
		Locale locale = getDefLocale();
		
		for (int j = 0; j < gcclist.size(); j++) {
			ChannelContent chcontent = (ChannelContent) gcclist.get(j);
			if (chcontent.getType().equals(ChannelContent.TYPE_DMENU)) {
				List<DocumentCategory> list = new ArrayList();
				if (chcontent.getDoccate() != null) {
					DocumentCategory dc = new DocumentCategory();
					dc.setPid(chcontent.getDoccate());
					dc.setLocale(locale.getId());
					list = this.documentCategoryService.findByDocumentCategory(dc);
				} else {
					list = this.documentCategoryService.findAllRoot(getDefLocale());
				}
				map.put(chcontent.getMarker(), list);
				map.put(chcontent.getMarker() + "_CurDoccate",
						chcontent.getDoccate());
			} else if (chcontent.getType().equals(ChannelContent.TYPE_DLIST)) {
				String doccateId = null;
				DocumentCategory doccate = chcontent.getDoccatee();
				if(doccate!=null){
					doccateId = doccate.getId();
				}
				
				Integer pageSize = chcontent.getNumber().intValue();
				int startIndex = chcontent.getOffset()
						.intValue();
				List<Document> list = this.documentService.findByDocumentOrNodePathLike(locale.getId(), doccateId,null);
				int totalCount = list.size();
				PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
				map.put(chcontent.getMarker(), ps);
			} else if (chcontent.getType().equals(ChannelContent.TYPE_DRCLIST)) {
				String doccateId = null;
				DocumentCategory doccate = chcontent.getDoccatee();
				if(doccate!=null){
					doccateId = doccate.getId();
				}
				
				Integer pageSize = chcontent.getNumber().intValue();
				int startIndex = chcontent.getOffset()
						.intValue();
				List<Document> list = this.documentService.findByDocumentOrNodePathLike(locale.getId(), doccateId,Document.MARKER_RECOMMAND);
				int totalCount = list.size();
				PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
				map.put(chcontent.getMarker(), ps);
			} else if (chcontent.getType().equals(ChannelContent.TYPE_DPAGELIST)) {
				String doccateId = null;
				DocumentCategory doccate = chcontent.getDoccatee();
				if(doccate!=null){
					doccateId = doccate.getId();
				}
				Integer pageSize = chcontent.getNumber().intValue();
				int startIndex = 1 ;
				
				List<Document> list = this.documentService.findByDocumentOrNodePathLike(locale.getId(), doccateId,null);
				int totalCount = list.size();
				PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
				map.put(chcontent.getMarker(), ps);
			} else if (chcontent.getType().equals(ChannelContent.TYPE_GMENU)) {
				List<Category> list = new ArrayList();
				if (chcontent.getCategory() != null) {
					Category dc= new Category();
					dc.setPid(chcontent.getCategoryy().getId());
					dc.setLocale(getDefLocale().getId());
					list = this.categoryService.findByCategory(dc);
				} else {
					list = this.categoryService.findAllRoot(getDefLocale());
				}
				map.put(chcontent.getMarker(), list);
			} else if (chcontent.getType().equals(ChannelContent.TYPE_GLIST)) {
				
				Category category = chcontent.getCategoryy();
				String[] keys = chcontent.getKeys();
				Integer pageSize = chcontent.getNumber().intValue();
				int startIndex = chcontent.getOffset()
						.intValue();
				String categoryId = null;
				if(category!=null){
					categoryId = category.getId();
				}
				List<Good> list = this.goodService.findByLocalAndCategory(locale.getId(), categoryId,null,keys);
		        int totalCount = list.size();
				PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
				map.put(chcontent.getMarker(), ps);
			} else if (chcontent.getType().equals(ChannelContent.TYPE_GRCLIST)) {
				Category category = chcontent.getCategoryy();
				String[] keys = chcontent.getKeys();
				Integer pageSize = chcontent.getNumber().intValue();
				int startIndex = chcontent.getOffset()
						.intValue();
				String categoryId = null;
				if(category!=null){
					categoryId = category.getId();
				}
				List<Good> list = this.goodService.findByLocalAndCategory(locale.getId(), categoryId,Good.STATUS_RECOMMAND,null);//keys
		        int totalCount = list.size();
				PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
				map.put(chcontent.getMarker(), ps);
			} else if (chcontent.getType().equals(ChannelContent.TYPE_GPAGELIST)) {
				Category category = chcontent.getCategoryy();
				String[] keys = chcontent.getKeys();
				Integer pageSize = chcontent.getNumber().intValue();
				int startIndex = 1;
				String categoryId = null;
				if(category!=null){
					categoryId = category.getId();
				}
				List<Good> list = this.goodService.findByLocalAndCategory(locale.getId(), categoryId,null,null);
		        int totalCount = list.size();
				PaginationSupport ps = new PaginationSupport(list, totalCount,startIndex,pageSize);
				map.put(chcontent.getMarker(), ps);
			} else if (chcontent.getType().equals(ChannelContent.TYPE_DSINGLE)) {
				Document document = chcontent.getDocumentt();
				map.put(chcontent.getMarker(), document);
			}
		}
		return map;
	}

	public String getUuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public Timestamp getTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public Member getMemberFromSession() {
		return (Member) ActionContext.getContext().getSession().get("member");
	}

	public Manager getManagerFromSession() {
		return (Manager) ActionContext.getContext().getSession().get("manager");
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
	}

}
