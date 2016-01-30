package com.ffyc.server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffyc.server.mapper.AddressMapper;
import com.ffyc.server.model.Address;
import com.ffyc.server.service.AddressService;
import com.ffyc.server.utils.PaginationSupport;

@Service("addressService")
public class AddressServiceImpl extends BaseServiceImpl implements
		AddressService {

	@Autowired
	private AddressMapper addressMapper;

	@Override
	public void save(Address address) {
		addressMapper.save(address);
	}

	@Override
	public void update(Address address) {
		addressMapper.update(address);
	}

	@Override
	public void delete(String id) {
		addressMapper.delete(id);
	}

	@Override
	public Address findById(String id) {
		return addressMapper.findById(id);
	}

	@Override
	public List<Address> findAll() {
		return addressMapper.findAll();
	}

	@Override
	public List<Address> findByAddress(Address dc) {
		return addressMapper.findByAddress(dc, null, null);
	}

	@Override
	public List<Address> findByAddress(Address dc, Integer startIndex,
			Integer pageSize) {
		return addressMapper.findByAddress(dc, startIndex, pageSize);
	}

	@Override
	public int getCountByAddress(Address dc) {
		return addressMapper.getCountByAddress(dc);
	}

	@Override
	public PaginationSupport findPageByAddress(Address dc, Integer page,
			Integer pageSize) {
		if (page <= 0)
			page = 1;
		int startIndex = (page - 1) * pageSize;
		int totalCount = this.addressMapper.getCountByAddress(dc);
		List list = this.addressMapper.findByAddress(dc, startIndex, pageSize);
		PaginationSupport ps = new PaginationSupport(list, totalCount,
				startIndex, pageSize);
		return ps;
	}

}
