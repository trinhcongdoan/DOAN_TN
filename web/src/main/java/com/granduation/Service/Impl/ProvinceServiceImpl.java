package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Province;
import com.granduation.JPARepository.ProvinceReponsitory;
import com.granduation.Service.IProvinceService;


@Service
public class ProvinceServiceImpl implements IProvinceService{

	@Autowired
	private ProvinceReponsitory province_repo;
	@Override
	public List<Province> findAllProvince() {
		return province_repo.findAll();
	}
	@Override
	public Province getById(int id) {
		return province_repo.getById(id);
	}

}
