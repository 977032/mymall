package com.ffyc.server.action.member;

import java.util.List;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Member;
import com.ffyc.server.model.OrderItem;
import com.ffyc.server.utils.Notice;
import com.ffyc.server.utils.PaginationSupport;

public class AddressSelAction extends BaseAction {
	
	private static final long serialVersionUID = -4441802063711615045L;
	private Notice notice = new Notice();
	private PaginationSupport ps;
	private int pagesize = 12;
	private int page = 1;
	private Member member;
	private List districtroot;

	public String execute() throws Exception {
		OrderItem dc = new OrderItem();
		dc.setMember(getMemberFromSession().getId());
		dc.setOrder("null");
		this.ps = this.orderItemService.findPageByOrderItem(dc, page, pagesize);
		if ((this.ps.getItems() != null) && (this.ps.getItems().size() > 0)) {
			this.member = this.memberService.findById(getMemberFromSession().getId());
			this.districtroot = this.districtService.findAllRoot();
			return "success";
		}
		this.notice.setStatus(false);
		this.notice.setCode("cart is null");
		this.notice.setTitle("提示：");
		this.notice.setBody("购物车中没有商品。");
		return "notice";
	}

	public Notice getNotice() {
		return this.notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public PaginationSupport getPs() {
		return this.ps;
	}

	public void setPs(PaginationSupport ps) {
		this.ps = ps;
	}

	public int getPagesize() {
		return this.pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List getDistrictroot() {
		return this.districtroot;
	}

	public void setDistrictroot(List districtroot) {
		this.districtroot = districtroot;
	}
}
