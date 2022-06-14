package com.granduation.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.granduation.Entity.Province;


@Service
public interface IProvinceService {

	public List<Province> findAllProvince();
	public Province getById(int id);
}
