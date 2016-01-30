package com.ffyc.server.action.publicer;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.Cookie;

import com.ffyc.server.action.BaseAction;
import com.ffyc.server.model.Brand;
import com.ffyc.server.model.Good;
import com.ffyc.server.model.Spec;
import com.ffyc.server.model.SpecItem;
import com.ffyc.server.model.SpecValue;
import com.ffyc.server.utils.PaginationSupport;

public class GoodAction extends BaseAction {
	private static final long serialVersionUID = -7057490756530517233L;
	private String gid;
	private Good good;
	private Set<SpecItem> specitems = new HashSet();
	private PaginationSupport brandPs;
	private PaginationSupport reGoodsPs;

	public String execute() throws Exception {
		GlobalChannelContent(this.map);
		this.good = this.goodService.findById(this.gid);
		if (this.good != null) {
			int i = 0;
			for (Iterator itSpec = this.good.getSpecs().iterator(); itSpec.hasNext();) {
				Spec spec = (Spec) itSpec.next();
				Iterator itSpecValue = spec.getSpecvalues().iterator();

				SpecValue specvalue = (SpecValue) itSpecValue.next();
				SpecItem specitem = specvalue.getSpecitemm();
				if (this.specitems.contains(specitem)) {
					Iterator itSpecItem = this.specitems.iterator();
					while (itSpecItem.hasNext()) {
						SpecItem si = (SpecItem) itSpecItem.next();
						if (specitem.equals(si)) {
							si.getSpecvalues().add(specvalue);
						}
					}
				} else {
					specitem.setSpecvalues(new ArrayList<SpecValue>());
					specitem.getSpecvalues().add(specvalue);
					this.specitems.add(specitem);
				}
				i++;
			}
			
			for (Iterator itSpecItem = this.specitems.iterator(); itSpecItem.hasNext();) {
				SpecItem specItem = (SpecItem) itSpecItem.next();
				Iterator itSpecValue = specItem.getSpecvalues().iterator();
				SpecValue sp = (SpecValue) itSpecValue.next();
			}
			String name = URLEncoder.encode(this.good.getName(), "UTF-8");
			Cookie cookie = new Cookie(this.good.getId(), name);
			cookie.setPath("/");
			cookie.setMaxAge(31536000);
			this.response.addCookie(cookie);
		}

		Brand bdc = new Brand();
		int pagesize = 12;
		int page = 1;
		this.brandPs = this.brandService.findPageByBrand(bdc, page, pagesize);

		Good gdc = new Good();
		//gdc.setStatus("recommend");
		gdc.setLocale(getDefLocale().getId());
		this.reGoodsPs = this.goodService.findPageByGood(gdc, page, pagesize);

		return "success";
	}

	public String getGid() {
		return this.gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public Good getGood() {
		return this.good;
	}

	public void setGood(Good good) {
		this.good = good;
	}

	public Set<SpecItem> getSpecitems() {
		return this.specitems;
	}

	public void setSpecitems(Set<SpecItem> specitems) {
		this.specitems = specitems;
	}

	public PaginationSupport getBrandPs() {
		return this.brandPs;
	}

	public void setBrandPs(PaginationSupport brandPs) {
		this.brandPs = brandPs;
	}

	public PaginationSupport getReGoodsPs() {
		return this.reGoodsPs;
	}

	public void setReGoodsPs(PaginationSupport reGoodsPs) {
		this.reGoodsPs = reGoodsPs;
	}
}
