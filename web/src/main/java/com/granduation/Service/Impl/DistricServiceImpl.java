package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.District;
import com.granduation.JPARepository.DistrictReponsitory;
import com.granduation.Service.IDistrictService;


@Service
public class DistricServiceImpl implements IDistrictService {

	@Autowired
	private DistrictReponsitory district_repo;

	@Override
	public List<District> findByProvinceId(int id) {
		return district_repo.findByProvinceId(id);
	}
}
