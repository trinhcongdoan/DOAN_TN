package com.granduation.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.granduation.Entity.Ward;
import com.granduation.JPARepository.WardRepinsitory;
import com.granduation.Service.IWardService;

@Service
public class WardServiceImpl implements IWardService {

	@Autowired
	private WardRepinsitory ward_repo;

	@Override
	public List<Ward> findByDistrictId(int id) {
		return ward_repo.findByDistrictId(id);
	}
}
