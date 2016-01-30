package com.ffyc.server.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.json.annotations.JSON;

import com.ffyc.server.mapper.AddressMapper;
import com.ffyc.server.mapper.DistrictMapper;
import com.ffyc.server.utils.BeanFactory;

public class District implements Serializable {
	public District() {}

	private Integer id;
	private District district;
	private Integer pid;
	private String name;
	private List<Area> areas;
	private List<District> districts;
	private List<Address> addresses;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JSON(serialize = false)
	public District getDistrict() {
		if (this.pid!=null  && district == null) {
			DistrictMapper districtMapper = BeanFactory.getInstance()
					.getDistrictMapper();
			district = districtMapper.findById(pid);
		}
		return this.district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSON(serialize = false)
	public List<Area> getAreas() {
		if(id!=null){
			DistrictMapper districtMapper = BeanFactory.getInstance().getDistrictMapper();
			this.areas = districtMapper.getAreas(id);
		}
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	@JSON(serialize = false)
	public List<District> getDistricts() {
		if(id!=null && this.districts ==null){
			DistrictMapper districtMapper = BeanFactory.getInstance().getDistrictMapper();
			District dc = new District();
			dc.setPid(id);
			this.districts = districtMapper.findByDistrict(dc, null, null);
		}
		return this.districts;
	}

	public void setDistricts(List<District> districts) {
		this.districts = districts;
	}

	@JSON(serialize = false)
	public List<Address> getAddresses() {
		if(this.addresses == null){
			AddressMapper addressMapper = BeanFactory.getInstance().getAddressMapper();
			Address dc = new Address();
			dc.setDistrict(String.valueOf(id));
			this.addresses = addressMapper.findByAddress(dc, null, null);
		}
		return this.addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
